package Integration;

import backend.Application;
import backend.api.CreateJobRequest;
import backend.domain.Job;
import backend.domain.JobResult;
import backend.domain.JobStatus;
import backend.infrastructure.JobFactory;
import backend.recovery.RecoveryExecutor;
import backend.repository.JobRepository;
import backend.repository.JobResultRepository;
import backend.service.WorkerService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(classes = Application.class, properties = "worker.count=0")
@ActiveProfiles("test")
public class JobStatusModellTest {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobResultRepository resultRepository;
    @Autowired
    private RecoveryExecutor recoveryExecutor;
    @Autowired
    private EntityManager entityManager;

    static final AtomicInteger finishWins = new AtomicInteger();
    static final AtomicInteger recoveryWins = new AtomicInteger();

    static String jobResult;

    @BeforeAll
    static void setup() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("Ergebnis", "Test");
        jobResult = mapper.writeValueAsString(jsonObjekt);
    }

    @BeforeEach
    void init() {
        entityManager.clear();
        resultRepository.deleteAllInBatch();
        jobRepository.deleteAllInBatch();
    }

    @AfterAll
    static void reportRaceCoverage() {
        System.out.println("finishJob gewann: " + finishWins.get() + ", Recovery gewann: " + recoveryWins.get());
        assertTrue(finishWins.get() > 0 && recoveryWins.get() > 0,
                "Beide Ausgänge sollten über die Wiederholungen hinweg mindestens einmal aufgetreten sein -- sonst war das Rennen nicht scharf genug, um Z-5 wirklich zu prüfen");
    }

    @RepeatedTest(100)
    void finishJobAndRecoveryRaceNeverReversesTerminalState() throws Exception {
        System.out.println("---------------------------------------Testdurchlauf---------------------------------------");
        UUID idempotencyKey = UUID.randomUUID();
        UUID workerId = UUID.randomUUID();

        Job tempJob = JobFactory.createJob(new CreateJobRequest(idempotencyKey, "{\"payload\":\"Test\"}"));
        tempJob.setStatus(JobStatus.RUNNING);
        tempJob.setClaimed_by(workerId);
        tempJob.setLease_until(Instant.now().plusSeconds(30));
        tempJob.setAttempt_count(5);
        jobRepository.save(tempJob);
        UUID tempJobId = tempJob.getId();

        CyclicBarrier barrier = new CyclicBarrier(2);
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Callable<Exception> finishTask = () -> {
            try {
                barrier.await();
                workerService.finishJob(tempJobId, workerId, new JobResult(tempJob, jobResult));
                return null;
            } catch (Exception e) {
                return e;
            }
        };
        Callable<Exception> recoveryTask = () -> {
            try {
                barrier.await();
                recoveryExecutor.recoverJob(tempJobId);
                return null;
            } catch (Exception e) {
                return e;
            }
        };

        Future<Exception> finishOutcome = pool.submit(finishTask);
        Future<Exception> recoveryOutcome = pool.submit(recoveryTask);
        Exception finishException = finishOutcome.get(10, TimeUnit.SECONDS);
        Exception recoveryException = recoveryOutcome.get(10, TimeUnit.SECONDS);
        pool.shutdown();

        assertNull(recoveryException, "recoverCycle() sollte nie werfen, auch nicht bei Kollision");

        Job result = jobRepository.findByIdempotencyKey(idempotencyKey).orElseThrow();

        if (result.getStatus() == JobStatus.SUCCEEDED) {
            finishWins.incrementAndGet();
            assertNull(finishException, "finishJob hätte hier erfolgreich sein müssen");
            assertNotNull(result.getResult());
        } else {
            recoveryWins.incrementAndGet();
            assertTrue(result.getStatus() == JobStatus.QUEUED || result.getStatus() == JobStatus.FAILED);
            assertNotNull(finishException, "finishJob hätte hier per Fencing abgelehnt werden müssen");
            assertNull(result.getResult());
        }
    }
}
