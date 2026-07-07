package backend.service;

import backend.Exception.JobNotFoundException;
import backend.Exception.LeaseExpiredException;
import backend.domain.Job;
import backend.domain.JobResult;
import backend.domain.JobStatus;
import backend.repository.JobRepository;
import backend.repository.JobResultRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class WorkerService {

    private final int maximumJobDuration = 30;

    private final JobRepository jobRepository;
    private final JobResultRepository resultRepository;

    public WorkerService(JobRepository repository, JobResultRepository resultRepository) {
        this.jobRepository = repository;
        this.resultRepository = resultRepository;
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
    public void finishJob(UUID jobId, UUID workerId, JobResult jobResult) throws JobNotFoundException{ //TODO: hier muss jobResult zu resultData geändert werden und dann innerhalb dieser methode ein jobresult erzeugt und gespeichert werden

        Job job = jobRepository.findByIdForUpdate(jobId).orElseThrow(JobNotFoundException::new);

        if (job.getStatus() != JobStatus.RUNNING)
            throw new IllegalStateException("Job hat nicht den Status RUNNING, obwohl Worker Ergebnis speichern wollte");

        if (!workerId.equals(job.getClaimed_by()))
            throw new IllegalStateException("Worker hat keine Berechtigung, diesen Job zu bearbeiten");

        if (job.getLease_until().isBefore(Instant.now()))
            throw new LeaseExpiredException();

        job.setStatus(JobStatus.SUCCESSFUL);
        resultRepository.save(jobResult);
        job.setResult(jobResult);
        job.setUpdatedAt(Instant.now());
    }
}
