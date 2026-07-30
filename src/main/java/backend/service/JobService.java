package backend.service;

import backend.Exception.IdempotencyException;
import backend.api.CreateJobRequest;
import backend.infrastructure.JobFactory;
import backend.domain.Job;
import backend.repository.JobRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
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
    public Job createJob(CreateJobRequest request) throws IdempotencyException {
        Job job = JobFactory.createJob(request);
        try{
            repository.saveAndFlush(job);
            return job;
        } catch (DataIntegrityViolationException e){
            if(!isConstraintViolation(e)){
                throw e;
            }
            Optional<Job> existingJob = repository.findByIdempotencyKey(request.getIdempotencyKey());
            if (existingJob.isPresent()){
                throw new IdempotencyException(existingJob.get());
            }
            throw e;
        }
    }

    public List<Job> getAllJobs() {
        return repository.findAll();
    }

    public Optional<Job> getJobById(UUID id) {
        return repository.findByIdempotencyKey(id);
    }

    private boolean isConstraintViolation(DataIntegrityViolationException e) {
        return e.getMostSpecificCause() instanceof ConstraintViolationException;
    }
}
