package jobClient.service;

import jobClient.dto.*;
import jobClient.rest.BackendRestClient;
import org.springframework.http.ResponseEntity;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.util.List;
import java.util.UUID;

public class JobClient {

    private final BackendRestClient client;

    public JobClient() {
        this.client = new BackendRestClient();
    }

    public ResponseEntity<CreateJobResponse> createJob() {
        //TODO Parameter ergänzen und Payload an Fachlogik anpassen --> Vorsicht bei JobClientTest wenn hier die payload geändert wird
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("name", "Max Mustermann");
        jsonObjekt.put("alter", 30);
        jsonObjekt.put("aktiv", true);
        jsonObjekt.putPOJO("hobbys", new String[]{"Laufen", "Zocken"});
        return client.createJob(new CreateJobRequest(UUID.randomUUID(), mapper.writeValueAsString(jsonObjekt)));
    }

    public ResponseEntity<JobResponse> getJob(String idempotencyKey){
        return client.getJob(UUID.fromString(idempotencyKey));
    }

    public ResponseEntity<List<JobResponse>> getAllJobs(){
        return client.getAllJobs();
    }

    public static void main(String[] args) {

//        Job job = client.getJob(UUID.fromString("da24d963-9af3-4db5-893d-bfa8e7d7f5e4")).getBody();
//        System.out.println(job);
//TODO: hier in Zukunft den Retry Executor als Zwischenschicht aufrufen, anstatt direkt den BackendRestClient
    }
}
