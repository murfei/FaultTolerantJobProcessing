package backend.recovery;

import backend.domain.Job;
import backend.domain.JobStatus;
import backend.repository.JobRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
public class RecoveryExecutor {

    private final int maxAttempts;
    private final JobRepository repository;

    public RecoveryExecutor(JobRepository repository, @Value( "${job.max-attempts}") int maxAttempts) {
        this.repository = repository;
        this.maxAttempts = maxAttempts;
    }

    @Transactional
    public void recoverJob(UUID id) {
        Job job = repository.findByIdForUpdate(id).orElse(null);
        if (job == null) {
            return;
        }
        if (job.getStatus() != JobStatus.RUNNING) {
            System.out.println("Recovery: Job hat nicht den Status RUNNING sondern " + job.getStatus() + ", skipping recovery");
            return;
        }
        if (job.getAttempt_count() < maxAttempts) {
            job.setStatus(JobStatus.QUEUED);
            System.out.println("Recovery: Job " + job.getIdempotencyKey() + " recovered and put back into queue");
        } else {
            job.setStatus(JobStatus.FAILED);
            System.out.println("Recovery: Job " + job.getIdempotencyKey() + " failed due to too many failed attempts");
        }
        job.setClaimed_by(null);
        job.setLease_until(null);
        job.setUpdatedAt(Instant.now());
    }
}
