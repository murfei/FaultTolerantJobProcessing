package api;

import domain.Job;
import org.springframework.web.bind.annotation.*;
import repository.JobRepository;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final JobRepository repository;

    JobController(JobRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/test")
    String test() {
        return "test";
    }

    @PostMapping("/job")
    Job createJob(@RequestBody Job newJob) {
        return null;
    }
}
