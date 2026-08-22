package backend.service;

import backend.Exception.JobNotFoundException;
import backend.Exception.LeaseExpiredException;
import backend.domain.Job;
import backend.domain.JobResult;
import backend.domain.JobStatus;
import backend.infrastructure.TestHook;
import backend.repository.JobRepository;
import backend.repository.JobResultRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkerService {


    private final int maximumJobDuration;

    private final JobRepository jobRepository;
    private final JobResultRepository resultRepository;
    private final TestHook testHook;

    public WorkerService(JobRepository repository, JobResultRepository resultRepository, TestHook testHook,
                         @Value( "${job.max-duration}") int maximumJobDuration) {
        this.jobRepository = repository;
        this.resultRepository = resultRepository;
        this.maximumJobDuration = maximumJobDuration;
        this.testHook = testHook;
    }

    @Transactional
    public Optional<Job> claimNextJob(UUID workerId){

        Optional<Job> result = jobRepository.findByStatus(JobStatus.QUEUED, 1);

        if(result.isEmpty()){
            return Optional.empty();
        }

        Job job = result.get();
        job.setStatus(JobStatus.RUNNING);
        job.setClaimed_by(workerId);
        job.setAttempt_count(job.getAttempt_count() + 1);
        job.setLease_until(Instant.now().plusSeconds(maximumJobDuration));
        job.setUpdatedAt(Instant.now());

        return Optional.of(job);
    }

    @Transactional
    public Job finishJob(UUID jobId, UUID workerId, JobResult jobResult) throws JobNotFoundException{

        Job job = jobRepository.findByIdForUpdate(jobId).orElseThrow(JobNotFoundException::new);

        if (job.getStatus() != JobStatus.RUNNING) {
            System.out.println("WorkerService: Job hat nicht den Status RUNNING sondern " + job.getStatus() + ", obwohl Worker Ergebnis speichern wollte");
            throw new IllegalStateException("Job hat nicht den Status RUNNING, obwohl Worker Ergebnis speichern wollte");
        }
        if (!workerId.equals(job.getClaimed_by()))
            throw new IllegalStateException("Worker hat keine Berechtigung, diesen Job zu bearbeiten");

        if (job.getLease_until().isBefore(Instant.now()))
            throw new LeaseExpiredException();

        job.setStatus(JobStatus.SUCCEEDED);
        testHook.afterStatusChange();
        resultRepository.save(jobResult);
        job.setResult(jobResult);
        job.setUpdatedAt(Instant.now());
        System.out.println("Job " + jobId + " finished successfully");
        return job;
    }
}
