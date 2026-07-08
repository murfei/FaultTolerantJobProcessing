package backend.api;

import backend.domain.JobStatus;

import java.time.Instant;
import java.util.UUID;

public class CreateJobResponse {

    private final UUID idempotencyKey;

    private final JobStatus status;

    private final Instant createdAt;

    private final String message;

    public CreateJobResponse(UUID idempotencyKey, JobStatus status, Instant createdAt) {
        this.idempotencyKey = idempotencyKey;
        this.status = status;
        this.createdAt = createdAt;
        message = null;
    }

    public CreateJobResponse(UUID idempotencyKey, JobStatus status, Instant createdAt, String message) {
        this.idempotencyKey = idempotencyKey;
        this.status = status;
        this.createdAt = createdAt;
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
