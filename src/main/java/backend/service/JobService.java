package backend.service;

import backend.api.CreateJobRequest;
import backend.infrastructure.JobFactory;
import backend.domain.Job;
import backend.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    public Job createJob(CreateJobRequest request) {
        Job job = JobFactory.createJob(request);
        repository.save(job);
        return job;
    }

    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    public Optional<Job> getJobById(UUID id) {
        return repository.findByIdempotencyKey(id);
    }
}
