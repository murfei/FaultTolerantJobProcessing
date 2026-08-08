package backend.api;

import backend.domain.Job;
import backend.domain.JobStatus;

import java.time.Instant;
import java.util.UUID;

public class CreateJobResponse {

    private final UUID idempotencyKey;

    private final JobStatus status;

    private final Instant createdAt;

    public CreateJobResponse(Job job) {
        this.idempotencyKey = job.getIdempotencyKey();
        this.status = job.getStatus();
        this.createdAt = job.getCreatedAt();
    }

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public JobStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
