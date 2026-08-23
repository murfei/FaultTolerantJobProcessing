package Integration;

import backend.Application;
import backend.api.CreateJobRequest;
import backend.domain.Job;
import backend.domain.JobResult;
import backend.domain.JobStatus;
import backend.infrastructure.JobFactory;
import backend.repository.JobRepository;
import backend.repository.JobResultRepository;
import backend.service.WorkerService;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Application.class, properties = "worker.count=0")
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Erlaubt non-static @BeforeAll
@DirtiesContext
public class ConcurrencyTest {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    JobResultRepository resultRepository;
    @Autowired
    WorkerService workerService;
    @Autowired
    DataSource dataSource;

    int workerCount = 8;

    @BeforeAll
    void verifyMaxPoolSize() {
        assertInstanceOf(HikariDataSource.class, dataSource);
        HikariDataSource hikari = (HikariDataSource) dataSource;

        // Bricht die gesamte Suite sofort ab, falls der Wert nicht stimmt
        assertTrue(workerCount + 1 < hikari.getMaximumPoolSize(), "Hikari Maximum Pool Size entspricht nicht der Erwartung");
    }

    @BeforeEach
    void init() {
        resultRepository.deleteAllInBatch();
        jobRepository.deleteAllInBatch();
    }

    @RepeatedTest(TestConfig.RACE_REPETITIONS)
    void onlyOneWorkerClaimsContendedJob() throws Exception {
        System.out.println("---------------------------------------Testdurchlauf---------------------------------------");
        UUID idempotencyKey = UUID.randomUUID();
        Job job = JobFactory.createJob(new CreateJobRequest(idempotencyKey, "{\"payload\":\"Test\"}"));
        jobRepository.save(job);

        CyclicBarrier barrier = new CyclicBarrier(workerCount);
        ExecutorService pool = Executors.newFixedThreadPool(workerCount);
        try {
            List<Future<Optional<Job>>> futures = new ArrayList<>();
            for (int i = 0; i < workerCount; i++) {
                UUID workerId = UUID.randomUUID();
                futures.add(pool.submit(() -> {
                    barrier.await();
                    return workerService.claimNextJob(workerId);
                }));
            }

            int successCount = 0;
            for (Future<Optional<Job>> f : futures) {
                if (f.get(10, TimeUnit.SECONDS).isPresent()) {
                    successCount++;
                } else {
                    System.out.println("Kein Job gefunden");
                }
            }
            assertEquals(1, successCount, "Genau ein Worker darf den Job unter " + workerCount + " Konkurrenten beanspruchen");

            Job claimed = jobRepository.findByIdempotencyKey(idempotencyKey).orElseThrow();
            assertEquals(JobStatus.RUNNING, claimed.getStatus());
            assertNotNull(claimed.getClaimed_by());
        } finally {
            pool.shutdown();
        }
    }

    @RepeatedTest(TestConfig.RACE_REPETITIONS)
    void concurrentFinishAttemptsYieldExactlyOneStoredResult() throws Exception {
        System.out.println("---------------------------------------Testdurchlauf---------------------------------------");
        UUID idempotencyKey = UUID.randomUUID();
        UUID legitimateWorker = UUID.randomUUID();
        UUID staleWorker = UUID.randomUUID();

        Job job = JobFactory.createJob(new CreateJobRequest(idempotencyKey, "{\"payload\":\"Test\"}"));
        job.setStatus(JobStatus.RUNNING);
        job.setClaimed_by(legitimateWorker);
        job.setLease_until(Instant.now().plusSeconds(30));
        job.setAttempt_count(1);
        jobRepository.save(job);
        UUID jobId = job.getId();

        CyclicBarrier barrier = new CyclicBarrier(2);
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Callable<Exception> legitimateTask = () -> {
            try {
                barrier.await();
                Job freshJob = jobRepository.findByIdempotencyKey(idempotencyKey).orElseThrow();
                workerService.finishJob(jobId, legitimateWorker, new JobResult(freshJob, "{\"Ergebnis\":\"legitim\"}"));
                return null;
            } catch (Exception e) {
                return e;
            }
        };
        Callable<Exception> staleTask = () -> {
            try {
                barrier.await();
                Job freshJob = jobRepository.findByIdempotencyKey(idempotencyKey).orElseThrow();
                workerService.finishJob(jobId, staleWorker, new JobResult(freshJob, "{\"Ergebnis\":\"verspätet\"}"));
                return null;
            } catch (Exception e) {
                return e;
            }
        };

        Future<Exception> legitimateOutcome = pool.submit(legitimateTask);
        Future<Exception> staleOutcome = pool.submit(staleTask);
        Exception legitimateException = legitimateOutcome.get(10, TimeUnit.SECONDS);
        Exception staleException = staleOutcome.get(10, TimeUnit.SECONDS);
        pool.shutdown();

        assertNull(legitimateException, "Der aktuelle Besitzer sollte trotz Konkurrenz erfolgreich abschließen können");
        assertNotNull(staleException, "Der verspätete Worker sollte per Fencing abgelehnt werden");
        assertInstanceOf(IllegalStateException.class, staleException);

        long resultCount = resultRepository.findAll().stream()
                .filter(r -> r.getJobId().equals(jobId))
                .count();
        assertEquals(1, resultCount);

        Job finished = jobRepository.findByIdempotencyKey(idempotencyKey).orElseThrow();
        assertEquals(JobStatus.SUCCEEDED, finished.getStatus());
        assertEquals("{\"Ergebnis\": \"legitim\"}", finished.getResult().getResult());
    }
}
