package Integration;

import backend.Application;
import backend.api.CreateJobRequest;
import backend.domain.Job;
import backend.domain.JobStatus;
import backend.service.JobService;
import backend.worker.JobProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class, properties = {
        "recovery.intervall=1",
        "job.processing-time=100",
        "job.max-duration=1"
})
@DirtiesContext
public class JobTerminationTest {

    @Autowired
    private JobService jobService;

    @MockitoSpyBean
    JobProcessor processor;

    @Test
    void terminationAfterTooManyFailuresTest(@Value( "${job.max-attempts}") int maxAttempts) throws Exception{
        UUID idempotencyKey = UUID.randomUUID();
        configureMockProcessor(idempotencyKey);
        System.out.println("Test gestartet. Idempotenz-Key: " + idempotencyKey);

        //Test, dass Jobs prinzipiell noch verarbeitet werden können
        Job job = jobService.createJob(new CreateJobRequest(UUID.randomUUID(), "{\"payload\":\"Test\"}"));
        assertEquals(JobStatus.QUEUED, job.getStatus());
        await().atMost(Duration.ofSeconds(20))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> jobService.getJobById(job.getIdempotencyKey())
                        .map(j -> j.getStatus() == JobStatus.SUCCEEDED)
                        .orElse(false));
        System.out.println("Sanity-Check erfolgreich abgeschlossen");

        Job failureJob = jobService.createJob(new CreateJobRequest(idempotencyKey, "{\"payload\":\"Test\"}"));
        assertEquals(JobStatus.QUEUED, failureJob.getStatus());
        System.out.println("Status QUEUED");
        Instant start = Instant.now();
        await().atMost(Duration.ofSeconds(60))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> jobService.getJobById(idempotencyKey)
                        .map(j -> j.getStatus() == JobStatus.FAILED)
                        .orElse(false));
        Instant end = Instant.now();
        failureJob = jobService.getJobById(idempotencyKey).orElseThrow();
        assertEquals(maxAttempts, failureJob.getAttempt_count());
        assertEquals(JobStatus.FAILED, failureJob.getStatus());
        double duration = Duration.between(start, end).toMillis() / 1000.0;
        assertTrue(duration <= maxAttempts * (1 + 2L)); //Zeit <= maxAttempts * (Lease(1) + max(recoveryIntervall(1),WorkerIdleTimeout(2))
        System.out.println("Es dauerte " + duration + " Sekunden, bis der Job abgeschlossen wurde");
    }

    private void configureMockProcessor(UUID idempotencyKey) throws Exception {
        doThrow(new RuntimeException("Simulierter permanenter Fachfehler"))
                .when(processor).process(argThat(job -> idempotencyKey.equals(job.getIdempotencyKey())));
    }
}
