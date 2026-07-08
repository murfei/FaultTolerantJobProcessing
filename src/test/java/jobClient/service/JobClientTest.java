package jobClient.service;

import backend.Application;
import backend.domain.JobStatus;
import jobClient.dto.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class JobClientTest {
  private static JobClient client;

  @BeforeAll
    static void setup() {
      client = new JobClient();
    }

    @Test
    void testCreate(){
        ResponseEntity<CreateJobResponse> response = client.createJob();
        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody().getIdempotencyKey() != null;
        assert response.getBody().getStatus() == JobStatus.QUEUED;
    }

    @Test
    void testGet(){
        UUID id = client.createJob().getBody().getIdempotencyKey();

        ResponseEntity<JobResponse> response = client.getJob(id);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(id, response.getBody().getIdempotencyKey());
        assertEquals(JobStatus.QUEUED, response.getBody().getStatus());
    }

    @Test
    void testGetAll(){
        ResponseEntity<List<JobResponse>> response = client.getAllJobs();
        assert response.getStatusCode().is2xxSuccessful();
        assert !response.getBody().isEmpty();
        for (JobResponse job : response.getBody()) {
            assert job.getIdempotencyKey() != null;
            assert job.getStatus() == JobStatus.QUEUED;
        }
    }
}
