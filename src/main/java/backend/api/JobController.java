package backend.api;

import backend.infrastructure.JobMapper;
import backend.recovery.DBRetryPolicy;
import backend.service.JobService;
import backend.domain.Job;
import common.retry.RetryExecutor;
import jakarta.validation.Valid;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;
    private final RetryExecutor retryExecutor;

    public JobController(JobService jobService) {
        this.jobService = jobService;
        this.retryExecutor = new RetryExecutor(new DBRetryPolicy());
    }

    @PostMapping("/job")
    public ResponseEntity<CreateJobResponse> createJob(@Valid @RequestBody CreateJobRequest request) {
        try {//TODO: Validator einbauen, der einkommende Anfragen auf richtigkeit validiert
            Job job = retryExecutor.execute(() -> jobService.createJob(request));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new CreateJobResponse(job.getIdempotencyKey(), job.getStatus(), job.getCreatedAt()));
        } catch (DataIntegrityViolationException e){
            if (!isConstraintViolation(e)) throw e;
            Job job = jobService.getJobById(request.getIdempotencyKey()).orElseThrow(() ->
                    new IllegalStateException("Unique Constraint Violation aber Job nicht gefunden.")
            );
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new CreateJobResponse(job.getIdempotencyKey(), job.getStatus(), job.getCreatedAt(), "Job already exists"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new CreateJobResponse(null, null, null, e.getMessage()));
        }
    }

    @GetMapping("/job")
    public ResponseEntity<List<JobDto>> getJobs() {
        try {
            return ResponseEntity.status(200).body(retryExecutor.execute(() -> jobService.getAllJobs().stream().map(JobMapper::toDTO).toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobDto> getJobs(@PathVariable UUID id) {
        try {
            return retryExecutor.execute(() -> jobService.getJobById(id))
                    .map(value -> ResponseEntity.status(200).body(JobMapper.toDTO(value)))
                    .orElseGet(() -> ResponseEntity.status(404).body(null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    private boolean isConstraintViolation(DataIntegrityViolationException e) {
        return e.getMostSpecificCause() instanceof JdbcSQLIntegrityConstraintViolationException;
    }
}
