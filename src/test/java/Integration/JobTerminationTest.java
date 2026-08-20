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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import java.time.Duration;
import java.util.UUID;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doThrow;

@ActiveProfiles("test")
@SpringBootTest(classes = Application.class, properties = {
        "recovery.intervall=1",
        "job.processing-time=100",
        "job.max-duration=1"
})
public class JobTerminationTest {

    @Autowired
    private JobService jobService;

    @MockitoSpyBean
    JobProcessor processor;

    @Test
    void terminationAfterTooManyFailuresTest(@Value( "${job.max-attempts}") int maxAttempts) throws Exception{
        UUID idempotencyKey = UUID.randomUUID();
        configureMockProcessor(idempotencyKey);

        //Test, dass Jobs prinzipiell noch verarbeitet werden können
        Job job = jobService.createJob(new CreateJobRequest(UUID.randomUUID(), "{\"payload\":\"Test\"}"));
        assertEquals(JobStatus.QUEUED, job.getStatus());
        await().atMost(Duration.ofSeconds(20))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> jobService.getJobById(job.getIdempotencyKey())
                        .map(j -> j.getStatus() == JobStatus.SUCCEEDED)
                        .orElse(false));

        Job failureJob = jobService.createJob(new CreateJobRequest(idempotencyKey, "{\"payload\":\"Test\"}"));
        assertEquals(JobStatus.QUEUED, failureJob.getStatus());
        await().atMost(Duration.ofSeconds(60))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> jobService.getJobById(idempotencyKey)
                        .map(j -> j.getStatus() == JobStatus.FAILED)
                        .orElse(false));
        failureJob = jobService.getJobById(idempotencyKey).orElseThrow();
        assertEquals(maxAttempts, failureJob.getAttempt_count());
        assertEquals(JobStatus.FAILED, failureJob.getStatus());
    }

    private void configureMockProcessor(UUID idempotencyKey) throws Exception {
        doThrow(new RuntimeException("Simulierter permanenter Fachfehler"))
                .when(processor).process(argThat(job -> idempotencyKey.equals(job.getIdempotencyKey())));
    }
}
