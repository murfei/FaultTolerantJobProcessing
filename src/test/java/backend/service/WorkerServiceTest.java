package backend.service;

import backend.Exception.JobNotFoundException;
import backend.Exception.LeaseExpiredException;
import backend.api.CreateJobRequest;
import backend.domain.Job;
import backend.domain.JobResult;
import backend.domain.JobStatus;
import backend.repository.JobRepository;
import backend.repository.JobResultRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
public class WorkerServiceTest {

    @Autowired
    private WorkerService workerService;
    @Autowired
    private JobService jobService;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private JobResultRepository resultRepository;

    @AfterEach
    void cleanup() {
        resultRepository.deleteAllInBatch();
        jobRepository.deleteAllInBatch();
    }

    @Test
    void JobLifecycleTest() throws JobNotFoundException {
        UUID idempotencyKey = UUID.randomUUID();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("name", "Max Mustermann");
        jsonObjekt.put("alter", 30);
        jsonObjekt.put("aktiv", true);
        jsonObjekt.putPOJO("hobbys", new String[]{"Laufen", "Zocken"});
        jobService.createJob(new CreateJobRequest(idempotencyKey, mapper.writeValueAsString(jsonObjekt)));

        //Test Creation
        UUID workerId = UUID.randomUUID();
        Optional<Job> result = workerService.claimNextJob(workerId);
        assertTrue(result.isPresent());
        assertEquals(idempotencyKey, result.get().getIdempotencyKey());
        assertEquals(JobStatus.RUNNING, result.get().getStatus());
        assertEquals(1, result.get().getAttempt_count());
        assertEquals(workerId, result.get().getClaimed_by());
        assertTrue(result.get().getLease_until().isAfter(Instant.now()));

        //Test no job available
        UUID falseWorkerId = UUID.randomUUID();
        result = workerService.claimNextJob(falseWorkerId);
        assertTrue(result.isEmpty());

        //Test finishing job
        Job job = jobRepository.findByIdempotencyKey(idempotencyKey).orElseGet(() -> fail("Job nicht gefunden"));
        UUID jobId = job.getId();
        mapper = new ObjectMapper();
        jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("result", "Test");
        JobResult jobResult = new JobResult(job, mapper.writeValueAsString(jsonObjekt));

        assertThrows(JobNotFoundException.class, () -> workerService.finishJob(UUID.randomUUID(), workerId, jobResult));
        assertThrows(IllegalStateException.class, () -> workerService.finishJob(jobId, UUID.randomUUID(), jobResult));
        job = jobRepository.findByIdempotencyKey(idempotencyKey).orElseGet(() -> fail("Job nicht gefunden"));
        job.setStatus(JobStatus.QUEUED);
        jobRepository.save(job);
        assertThrows(IllegalStateException.class, () -> workerService.finishJob(jobId, workerId, jobResult));
        job.setStatus(JobStatus.RUNNING);
        job.setLease_until(Instant.now().minusSeconds(1));
        jobRepository.save(job);
        assertThrows(LeaseExpiredException.class, () -> workerService.finishJob(jobId, workerId, jobResult));
        job.setLease_until(Instant.now().plusSeconds(10));
        jobRepository.save(job);
        workerService.finishJob(jobId, workerId, jobResult);
        Job savedJob = jobRepository.findByIdempotencyKey(idempotencyKey).get();
        assertEquals(JobStatus.SUCCEEDED, savedJob.getStatus());

        mapper = new ObjectMapper();
        JsonNode expectedJson = mapper.readTree(jobResult.getResult());
        JsonNode actualJson = mapper.readTree(savedJob.getResult().getResult());
        assertEquals(expectedJson, actualJson);

    }
}
