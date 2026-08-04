package backend.service;

import backend.Exception.IdempotencyException;
import backend.Exception.JobNotFoundException;
import backend.api.CreateJobRequest;
import backend.infrastructure.JobFactory;
import backend.domain.Job;
import backend.repository.JobRepository;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.hibernate.annotations.NotFound;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobService {

    private final JobRepository repository;

    public JobService(JobRepository repository) {
        this.repository = repository;
    }

    @Transactional
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
