package jobClient.service;

import jobClient.dto.*;
import jobClient.rest.BackendRestClient;
import jobClient.retry.RetryExecutor;
import jobClient.retry.RetryPolicy;
import org.springframework.http.ResponseEntity;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

public class JobClient {

    private final RetryExecutor retryExecutor;
    private final BackendRestClient client;

    private final int maxAttempts =5;
    private final Duration delay = Duration.ofMillis(200);
    private final double backoffFactor = 2;

    public JobClient() {
        this.retryExecutor = new RetryExecutor(new RetryPolicy(maxAttempts, delay, backoffFactor));
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

        try {
            return retryExecutor.execute(() ->
                    client.createJob(new CreateJobRequest(UUID.randomUUID(), mapper.writeValueAsString(jsonObjekt)))
            );
        } catch (Exception e) {
            System.out.println("Der Job konnte nicht erfolgreich zugestellt werden. Der letzte Fehler war: " + e.getMessage());
            return null;
        }
    }

    public ResponseEntity<JobResponse> getJob(UUID idempotencyKey){
        try {
            return retryExecutor.execute(() ->
                    client.getJob(idempotencyKey)
            );
        } catch (Exception e) {
            System.out.println("Es konnte keine Antwort vom Server entgegengenommen werden. Die letzte Fehlermeldung lautet: " + e.getMessage());
            return null;
        }
    }

    public ResponseEntity<List<JobResponse>> getAllJobs(){
        try {
            return retryExecutor.execute(client::getAllJobs);
        } catch (Exception e) {
            System.out.println("Es konnte keine Antwort vom Server entgegengenommen werden. Die letzte Fehlermeldung lautet: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        JobClient client = new JobClient();
        client.createJob();
    }
}
