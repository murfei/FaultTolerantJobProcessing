package Integration;

import backend.Application;
import backend.domain.JobStatus;
import jobClient.dto.CreateJobResponse;
import jobClient.service.JobClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import static org.junit.Assert.*;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class JoberstellungTest {

    static JobClient client;
    static String payload;

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
    void validPayloadTest() {
        ResponseEntity<CreateJobResponse> response = client.createJob(payload);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(JobStatus.QUEUED, response.getBody().getStatus());
        assertNotNull(response.getBody().getIdempotencyKey());
    }

    @Test
    void invalidPayloadTest() {
        assertNull(client.createJob("invalid payload"));
    }

    //TODO: Idempotenztest
}
