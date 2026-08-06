package backend.api;

import backend.infrastructure.JobMapper;
import backend.recovery.DBRetryPolicy;
import backend.service.JobService;
import backend.domain.Job;
import backend.service.PayloadValidator;
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
    private final PayloadValidator validator;

    public JobController(JobService jobService, PayloadValidator validator) {
        this.jobService = jobService;
        this.retryExecutor = new RetryExecutor(new DBRetryPolicy());
        this.validator = validator;
    }

    @PostMapping("/job")
    public ResponseEntity<CreateJobResponse> createJob(@Valid @RequestBody CreateJobRequest request) {
        if (!validator.isValid(request.getPayload())){
            return ResponseEntity.badRequest().build();
        }
        try {
            Job job = retryExecutor.execute(() -> jobService.createJob(request));
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new CreateJobResponse(job));
        } catch (DataIntegrityViolationException e){
            if (!isConstraintViolation(e))
                throw e;
            Job job = jobService.getJobById(request.getIdempotencyKey())
                                .orElseThrow(IllegalStateException::new);
            return ResponseEntity.ok(new CreateJobResponse(job, "Job already exists"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
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
