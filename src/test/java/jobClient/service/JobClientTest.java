package jobClient.service;

import backend.Application;
import backend.domain.JobStatus;
import jobClient.dto.*;
import jobClient.rest.BackendRestClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static backend.domain.JobStatus.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class JobClientTest {
  private static JobClient client;
  private MockWebServer server;
  private static String payload;

  @BeforeAll
    static void setup() {
      client = new JobClient();
      ObjectMapper mapper = new ObjectMapper();
      ObjectNode jsonObjekt = mapper.createObjectNode();
      jsonObjekt.put("name", "Max Mustermann");
      jsonObjekt.put("alter", 30);
      jsonObjekt.put("aktiv", true);
      jsonObjekt.putPOJO("hobbys", new String[]{"Laufen", "Zocken"});
      payload = mapper.writeValueAsString(jsonObjekt);
    }

    @Test
    void testCreate(){
        ResponseEntity<CreateJobResponse> response = client.createJob(payload);
        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody().getIdempotencyKey() != null;
        assert response.getBody().getStatus() == QUEUED;
    }

    @Test
    void testGet(){
        UUID id = client.createJob(payload).getBody().getIdempotencyKey();

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
            assertTrue(Set.of(QUEUED, SUCCEEDED, FAILED, RUNNING).contains(job.getStatus()));
        }
    }

    @Test
    void testRetryToManyAttempts() throws IOException{
        JobClient client = innitServer();
        for (int i = 0; i < 6; i++) {
            server.enqueue(new MockResponse()
                    .setResponseCode(501));
        }
        client.getAllJobs();
        assertEquals(5, server.getRequestCount());
        server.shutdown();
    }

    @Test
    void testRetryNonRetriableRequest() throws IOException{
        JobClient client = innitServer();
        server.enqueue(new MockResponse()
                .setResponseCode(404));
        client.getAllJobs();
        assertEquals(1, server.getRequestCount());
        server.shutdown();
    }

    @Test
    void testRetrySuccessfulRetry() throws IOException{
      JobClient client = innitServer();

        for (int i = 0; i < 3; i++) {
            server.enqueue(new MockResponse()
                    .setResponseCode(501));
        }
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setHeader("Content-Type", "application/json")
                .setBody("""
            [
              {
                "idempotencyKey": "d3b07384-d113-49c6-a5e6-ec642d99d424",
                "status": "QUEUED",
                "createdAt": "2026-07-09T12:02:57.382708Z"
              }
            ]
            """));
        ResponseEntity<List<JobResponse>> response= client.getAllJobs();
        assertEquals(4, server.getRequestCount());
        assertEquals(1, response.getBody().size());
        assertEquals(JobStatus.QUEUED, response.getBody().getFirst().getStatus());
        server.shutdown();
    }

    private JobClient innitServer() throws IOException{
        server = new MockWebServer();
        server.start();
        BackendRestClient tempClient = new BackendRestClient(server.url("/api/jobs").toString());
        return new JobClient(tempClient);
    }
}
