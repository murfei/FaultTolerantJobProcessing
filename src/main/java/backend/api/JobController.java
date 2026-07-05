package backend.api;

import backend.application.JobService;
import backend.domain.Job;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.status(200).body("test");
    }

    @PostMapping("/job")
    Job createJob(@RequestBody Job newJob) {
        return null;
    }
}
