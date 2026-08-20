package Integration;

import backend.Application;
import backend.api.CreateJobRequest;
import backend.domain.Job;
import backend.domain.JobResult;
import backend.domain.JobStatus;
import backend.infrastructure.JobFactory;
import backend.repository.JobRepository;
import backend.repository.JobResultRepository;
import backend.service.RecoveryService;
import backend.service.WorkerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class WorkerCrashRecoveryTest {

    String payload;
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

    @BeforeEach
    void init() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("payload", "Test");
        payload = mapper.writeValueAsString(jsonObjekt);

         concurrentWorker = UUID.randomUUID();

        //verwaisten Job erstellen
        jobId = UUID.randomUUID();
        job = JobFactory.createJob(new CreateJobRequest(jobId, payload));
        job.setStatus(JobStatus.RUNNING);
        job.setClaimed_by(concurrentWorker);
        job.setLease_until(Instant.now().minusSeconds(10));
        job.setAttempt_count(1);
        resultRepository.deleteAll();
        jobRepository.deleteAll();
    }

    @Test
    void recoveryComponentTest() throws InterruptedException {
        //verwaisten Job persistieren
        jobRepository.save(job);
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

        //Testen, dass der Job nicht mehr von einem simulierten alten Worker fertiggestellt werden kann
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
        assertThrows(IllegalStateException.class, () -> workerService.finishJob(finishedJob.getId(),
                concurrentWorker, new JobResult(finishedJob, "Unberechtigter Worker")));
        assertEquals(countResults+1, resultRepository.count());
    }

    @Test
    void jobStatusFailedAfterToManyAttempsTest(@Value( "${job.max-attempts}") int maxAttempts){
        jobRepository.save(job);
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
}
