package jobClient.rest;

import backend.Application;
import backend.domain.JobStatus;
import backend.repository.JobRepository;
import jobClient.dto.CreateJobRequest;
import jobClient.dto.CreateJobResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class BackendRestClientTest {

    private static BackendRestClient client;
    private static ObjectMapper mapper;

    @Autowired
    private JobRepository jobRepository;

    @BeforeAll
    static void setup(){
        client = new BackendRestClient("http://localhost:8080/api/jobs");
        mapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown(){
        jobRepository.deleteAllInBatch();
    }

    @Test
    void creatTest(){
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("payload", "Test");
        CreateJobRequest request = new CreateJobRequest(UUID.randomUUID(), mapper.writeValueAsString(jsonObjekt));
        ResponseEntity<CreateJobResponse> response = client.createJob(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotEquals(null, response.getBody().getIdempotencyKey());
        assertEquals(JobStatus.QUEUED, response.getBody().getStatus());
    }

    @Test
    void idempotencyTest(){
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("payload", "Test");
        CreateJobRequest request = new CreateJobRequest(UUID.randomUUID(), mapper.writeValueAsString(jsonObjekt));
        System.out.println(request.getPayload());
        ResponseEntity<CreateJobResponse> response = client.createJob(request);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        int length = client.getAllJobs().getBody().size();
        response = client.createJob(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(JobStatus.QUEUED, response.getBody().getStatus());
        assertEquals(length, client.getAllJobs().getBody().size());
    }

}
