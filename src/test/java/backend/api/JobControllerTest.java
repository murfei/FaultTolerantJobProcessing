package backend.api;

import backend.Application;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = Application.class)
public class JobControllerTest {

    private static CreateJobRequest request;
    @Autowired
    private JobController controller;

    @BeforeAll
    static void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("name", "Max Mustermann");
        jsonObjekt.put("alter", 30);
        jsonObjekt.put("aktiv", true);
        jsonObjekt.putPOJO("hobbys", new String[]{"Laufen", "Zocken"});
        request = new CreateJobRequest(UUID.randomUUID(), mapper.writeValueAsString(jsonObjekt));
    }

    @Test
    void createJobTest(){
        //invalid JSON
        ResponseEntity<CreateJobResponse> response = controller.createJob(new CreateJobRequest(UUID.randomUUID(), "Test"));
        assertEquals(400, response.getStatusCode().value());
        //valid request
        response = controller.createJob(request);
        assertEquals(201, response.getStatusCode().value());
        //idempotency case
        response = controller.createJob(request);
        assertEquals(200, response.getStatusCode().value());
    }
}
