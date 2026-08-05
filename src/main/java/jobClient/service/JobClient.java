package jobClient.service;

import jobClient.dto.*;
import jobClient.rest.BackendRestClient;
import common.retry.RetryExecutor;
import jobClient.retry.RestMessageRetryPolicy;
import org.springframework.http.ResponseEntity;

import java.time.Duration;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class JobClient {

    private final RetryExecutor retryExecutor;
    private final BackendRestClient client;

    private final int maxAttempts =5;
    private final Duration delay = Duration.ofMillis(200);
    private final double backoffFactor = 2;
    private final String baseUrl = "http://localhost:8080/api/jobs";

    public JobClient() {
        this.retryExecutor = new RetryExecutor(new RestMessageRetryPolicy(maxAttempts, delay, backoffFactor));
        this.client = new BackendRestClient(baseUrl);
    }

    public JobClient(BackendRestClient client) {
        this.retryExecutor = new RetryExecutor(new RestMessageRetryPolicy(maxAttempts, delay, backoffFactor));
        this.client = client;
    }

    public ResponseEntity<CreateJobResponse> createJob(String payload) {
        try {
            return retryExecutor.execute(() ->
                    client.createJob(new CreateJobRequest(UUID.randomUUID(), payload))
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
        while(true) {
            Scanner input = new Scanner(System.in);
            input.next();
            client.createJob("Empty Job");
        }
    }
}
