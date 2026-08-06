package backend.api;

import backend.domain.Job;
import backend.domain.JobStatus;

import java.time.Instant;
import java.util.UUID;

public class CreateJobResponse {

    private final UUID idempotencyKey;

    private final JobStatus status;

    private final Instant createdAt;

    private final String message;

    public CreateJobResponse(Job job) {
        this.idempotencyKey = job.getIdempotencyKey();
        this.status = job.getStatus();
        this.createdAt = job.getCreatedAt();
        message = null;
    }

    public CreateJobResponse(Job job, String message) {
        this.idempotencyKey = job.getIdempotencyKey();
        this.status = job.getStatus();
        this.createdAt = job.getCreatedAt();
        this.message = message;
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

    public String getMessage() {
        return message;
    }
}
