package jobClient.rest;

import jobClient.dto.CreateJobRequest;
import jobClient.dto.CreateJobResponse;
import jobClient.dto.JobResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

public class BackendRestClient {

    private final String baseUrl = "http://localhost:8080/api/jobs";
    private final RestClient restClient;

    public BackendRestClient() {
        restClient = RestClient.create(baseUrl);
    }

    public ResponseEntity<CreateJobResponse> createJob(CreateJobRequest request) {
        return restClient.post().uri("/job").body(request).retrieve().toEntity(CreateJobResponse.class);
    }

    public ResponseEntity<JobResponse> getJob(UUID idempotencyKey) {
        return restClient.get().uri("/job/{id}", idempotencyKey).retrieve().toEntity(JobResponse.class);

    }

    public ResponseEntity<List<JobResponse>> getAllJobs() {
        return restClient.get().uri("/job").retrieve().toEntity(new ParameterizedTypeReference<>(){});
    }
}
