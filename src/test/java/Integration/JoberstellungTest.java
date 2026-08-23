package Integration;

import backend.Application;
import backend.domain.JobStatus;
import backend.repository.JobRepository;
import jobClient.dto.CreateJobRequest;
import jobClient.dto.CreateJobResponse;
import jobClient.rest.BackendRestClient;
import jobClient.service.JobClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class JoberstellungTest {

    static JobClient client;
    static String payload;
    static BackendRestClient restClient;
    @Autowired
    JobRepository jobRepository;

    @BeforeAll
    static void setup() {
        restClient = Mockito.spy(new BackendRestClient("http://localhost:8080/api/jobs"));
        client = new JobClient(restClient);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("name", "Max Mustermann");
        jsonObjekt.put("alter", 30);
        jsonObjekt.put("aktiv", true);
        jsonObjekt.putPOJO("hobbys", new String[]{"Laufen", "Zocken"});
        payload = mapper.writeValueAsString(jsonObjekt);
    }

    @AfterEach
    void tearDown() {
        jobRepository.deleteAllInBatch();
    }

    @Test
    void validPayloadTest() {
        ResponseEntity<CreateJobResponse> response = client.createJob(payload);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(JobStatus.QUEUED, response.getBody().getStatus());
        assertNotNull(response.getBody().getIdempotencyKey());
    }

    @Test
    void invalidPayloadTest() { // Nachweis zu Z1
        reset(restClient);
        int length = client.getAllJobs().getBody().size();
        System.out.println("Anzahl Jobs vor dem Test: " + length);
        assertNull(client.createJob("")); //Syntaktisch invalide Request -> scheitert an @Valid
        verify(restClient, times(1)).createJob(any(CreateJobRequest.class));
        assertNull(client.createJob("invalid payload")); //Fachlich invalide Request -> scheitert an Validator
        verify(restClient, times(2)).createJob(any(CreateJobRequest.class));
        assertEquals(length, client.getAllJobs().getBody().size());
        System.out.println("Anzahl Jobs nach dem Test: " + length);
    }

    @Test
    void duplicateRequestTest() {
        long countBefore = jobRepository.count();
        AtomicInteger callCount = new AtomicInteger();
        ClientHttpRequestInterceptor dropFirstResponse = (request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            if (callCount.getAndIncrement() == 0) {
                response.close();
                throw new IOException("Simulierter Verbindungsabbruch nach erfolgreicher Verarbeitung");
            }
            return response;
        };

        BackendRestClient restClient = new BackendRestClient("http://localhost:8080/api/jobs", dropFirstResponse);
        JobClient interceptorClient = new JobClient(restClient);

        System.out.println("Anzahl Jobs vor dem Test: " + jobRepository.count());
        ResponseEntity<CreateJobResponse> response = interceptorClient.createJob(payload);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        System.out.println("Statuscode: " + response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, callCount.get());
        long count = jobRepository.count();
        assertEquals(countBefore + 1, count);
        System.out.println("Anzahl Jobs nach dem Test: " + count);
    }
}
