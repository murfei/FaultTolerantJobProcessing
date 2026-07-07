package backend.api;

import backend.infrastructure.JobMapper;
import backend.service.JobService;
import backend.domain.Job;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping("/job")
    public ResponseEntity<CreateJobResponse> createJob(@Valid @RequestBody CreateJobRequest request) {
        try {
            Job job = jobService.createJob(request);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new CreateJobResponse(job.getIdempotencyKey(), job.getStatus(), job.getCreatedAt()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/job")
    public ResponseEntity<List<JobDto>> getJobs() {
        return ResponseEntity.status(200).body(jobService.getAllJobs().stream().map(JobMapper::toDTO).toList());
    }

    @GetMapping("/job/{id}")
    public ResponseEntity<JobDto> getJobs(@PathVariable UUID id) {
        return jobService.getJobById(id)
                .map(value -> ResponseEntity.status(200).body(JobMapper.toDTO(value)))
                .orElseGet(() -> ResponseEntity.status(404).body(null));
    }
}
