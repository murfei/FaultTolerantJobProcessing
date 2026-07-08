package jobClient.service;

import backend.Application;
import jobClient.dto.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static backend.domain.JobStatus.*;

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
        assert response.getBody().getStatus() == QUEUED;
    }

    @Test
    void testGet(){
        UUID id = client.createJob().getBody().getIdempotencyKey();

        ResponseEntity<JobResponse> response = client.getJob(id);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(id, response.getBody().getIdempotencyKey());
        assertEquals(QUEUED, response.getBody().getStatus());
    }

    @Test
    void testGetAll(){
        ResponseEntity<List<JobResponse>> response = client.getAllJobs();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertFalse(response.getBody().isEmpty());
        for (JobResponse job : response.getBody()) {
            assertNotEquals(null, job.getIdempotencyKey());
            assertTrue(Set.of(QUEUED, SUCCESSFUL, FAILED, RUNNING).contains(job.getStatus()));
        }
    }
}
