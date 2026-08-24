package Integration;

import backend.Application;
import backend.Exception.LeaseExpiredException;
import backend.api.CreateJobRequest;
import backend.domain.Job;
import backend.domain.JobResult;
import backend.domain.JobStatus;
import backend.infrastructure.JobFactory;
import backend.infrastructure.TestHook;
import backend.repository.JobRepository;
import backend.repository.JobResultRepository;
import backend.service.RecoveryService;
import backend.service.WorkerService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.*;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
@DirtiesContext
public class WorkerCrashRecoveryTest {

    static String payload;
    UUID jobId;
    Job job;
    UUID concurrentWorker;

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobResultRepository resultRepository;
    @Autowired
    private RecoveryService recoveryService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private EntityManager entityManager;

    @MockitoBean
    private TestHook testHook;

    @BeforeAll
    static void setup() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("payload", "Test");
        payload = mapper.writeValueAsString(jsonObjekt);
    }

    @BeforeEach
    void init() {
        concurrentWorker = UUID.randomUUID();
        //verwaisten Job erstellen
        jobId = UUID.randomUUID();
        job = JobFactory.createJob(new CreateJobRequest(jobId, payload));
        job.setStatus(JobStatus.RUNNING);
        job.setClaimed_by(concurrentWorker);
        job.setLease_until(Instant.now().minusSeconds(10));
        job.setAttempt_count(1);
        jobRepository.save(job);
    }

    @AfterEach
    void tearDown() {
        resultRepository.deleteAllInBatch();
        jobRepository.deleteAllInBatch();
    }

    @Test
    void recoveryComponentTest() throws InterruptedException {
        //RecoveryService aktivieren
        recoveryService.recoverCycle();

        //Status testen
        Job recoveredJob = jobRepository.findByIdempotencyKey(jobId).get();
        assertEquals(JobStatus.QUEUED, recoveredJob.getStatus());
        assertNull(recoveredJob.getClaimed_by());
        assertNull(recoveredJob.getLease_until());
        long countResults = resultRepository.count();

        //Warten, dass freier Worker den Job wieder aufnimmt
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofMillis(200))
                .until(() -> jobRepository.findByIdempotencyKey(jobId)
                        .map(j -> j.getStatus() == JobStatus.RUNNING)
                        .orElse(false));
        //Kontrolle, dass der Job nicht vom gleichen Worker wieder aufgenommen wurde
        assertNotEquals(jobRepository.findByIdempotencyKey(jobId).get().getClaimed_by(), concurrentWorker);

        //Testen, dass der Job nicht mehr von einem simulierten alten Worker fertiggestellt werden kann
        System.out.println("Simulierter Zugriff eines unberechtigten Workers");
        assertThrows(IllegalStateException.class, () -> workerService.finishJob(recoveredJob.getId(),
                concurrentWorker, new JobResult(recoveredJob, "Unberechtigter Worker")));

        //Auf Fertigstellung warten
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofMillis(200))
                .until(() -> jobRepository.findByIdempotencyKey(jobId)
                        .map(j -> j.getStatus() == JobStatus.SUCCEEDED)
                        .orElse(false));

        Job finishedJob = jobRepository.findByIdempotencyKey(jobId).orElseThrow(IllegalStateException::new);
        //Fertigstellung prüfen
        assertEquals(JobStatus.SUCCEEDED, finishedJob.getStatus());
        assertEquals(2, finishedJob.getAttempt_count());
        assertNotNull(finishedJob.getResult());

        //Erneute Prüfung, dass keine Doppelspeicherung möglich ist, falls der alte Worker nur zu langsam war und doch
        //das Speichern versucht
        System.out.println("Simulierter Zugriff eines zu langsamen Workers");
        assertThrows(IllegalStateException.class, () -> workerService.finishJob(finishedJob.getId(),
                concurrentWorker, new JobResult(finishedJob, "Unberechtigter Worker")));
        assertEquals(countResults+1, resultRepository.count());
    }

    @Test
    void finishJobRejectedWhenOwnLeaseAlreadyExpiredButNotYetRecovered() {
        UUID workerId = UUID.randomUUID();
        Job job = JobFactory.createJob(new CreateJobRequest(UUID.randomUUID(), "{\"payload\":\"Test\"}"));
        job.setStatus(JobStatus.RUNNING);
        job.setClaimed_by(workerId); // weiterhin der rechtmäßige, noch nicht recoverte Besitzer
        job.setLease_until(Instant.now().minusSeconds(1)); // Lease ist abgelaufen, aber Recovery war noch nicht dran
        jobRepository.save(job);

        assertThrows(LeaseExpiredException.class, () ->
                workerService.finishJob(job.getId(), workerId, new JobResult(job, "{\"Ergebnis\":\"Zu spät\"}")));

        assertEquals(0, resultRepository.findAll().stream()
                .filter(r -> r.getJobId().equals(job.getId())).count());
    }
    @Test
    void jobStatusFailedAfterToManyAttempsTest(@Value( "${job.max-attempts}") int maxAttempts){
        for (int i = 1; i < maxAttempts; i++) {
            recoveryService.recoverCycle();
            job = jobRepository.findByIdempotencyKey(jobId).get();
            job.setStatus(JobStatus.RUNNING);
            job.setClaimed_by(concurrentWorker);
            job.setLease_until(Instant.now().minusSeconds(10));
            job.setAttempt_count(job.getAttempt_count() + 1);
            jobRepository.save(job);
        }
        recoveryService.recoverCycle();
        Job recoveredJob = jobRepository.findByIdempotencyKey(jobId).get();
        assertEquals(JobStatus.FAILED, recoveredJob.getStatus());
    }

    @Test
    void atomicSaveTest(){
        job.setLease_until(Instant.now().plusSeconds(10));
        jobRepository.save(job);
        JobResult result = new JobResult(job, "{\"result\":\"Test\"}");

        doThrow(new RuntimeException("Simulierter Fehler"))
                .when(testHook)
                .afterStatusChange();

        assertThrows(RuntimeException.class, () -> workerService.finishJob(job.getId(), concurrentWorker, result));
        System.out.println("Testhook hat Fehler geworfen");
//        entityManager.clear();

        Job persistedJob = jobRepository.findById(job.getId()).orElseThrow();
        assertEquals(JobStatus.RUNNING, persistedJob.getStatus());
        assertNull(persistedJob.getResult());
        assertFalse(resultRepository.existsById(job.getId()));
        System.out.println("Job:\nIdempotenz-Key: " + persistedJob.getIdempotencyKey() + "\nStatus: " + persistedJob.getStatus()
                + "\nErgebnis: " + persistedJob.getResult());
    }
}
