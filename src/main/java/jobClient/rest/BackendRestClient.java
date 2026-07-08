package jobClient.rest;

import jobClient.dto.CreateJobRequest;
import jobClient.dto.CreateJobResponse;
import jobClient.dto.JobResponse;
import jobClient.exception.NonRetryableHttpException;
import jobClient.exception.RetryableHttpException;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

public class BackendRestClient {

    private final String baseUrl = "http://localhost:8080/api/jobs";
    private final RestClient restClient;

    public BackendRestClient() {
        restClient = RestClient.builder()
                .baseUrl(baseUrl)
                .defaultStatusHandler(this::checkRetriable,
                        (req, res) -> {
                            throw new RetryableHttpException(res.getStatusCode(), "Potentially retriable error while requesting Job-Processing Backend-API");
                        })
                .defaultStatusHandler(HttpStatusCode::isError,
                        (req, res) -> {
                            throw new NonRetryableHttpException(res.getStatusCode(), "Not retriable error while requesting Job-Processing Backend-API");
                        })
                .build();
    }

    public ResponseEntity<CreateJobResponse> createJob(CreateJobRequest request) {
        return restClient.post()
                .uri("/job")
                .body(request)
                .retrieve()
                .toEntity(CreateJobResponse.class);
    }

    public ResponseEntity<JobResponse> getJob(UUID idempotencyKey) {
        return restClient.get()
                .uri("/job/{id}", idempotencyKey)
                .retrieve()
                .toEntity(JobResponse.class);
    }

    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return restClient.get()
                .uri("/job")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {
                });
    }

    private boolean checkRetriable(HttpStatusCode status) {
        return status.is5xxServerError()
                || status.value() == 408
                || status.value() == 429;
    }
}
