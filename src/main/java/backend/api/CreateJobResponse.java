package backend.api;

import backend.domain.JobStatus;

import java.time.Instant;
import java.util.UUID;

public class CreateJobResponse {

    private UUID idempotencyKey;

    private JobStatus status;

    private Instant createdAt;

    public CreateJobResponse(UUID idempotencyKey, JobStatus status, Instant createdAt) {
        this.idempotencyKey = idempotencyKey;
        this.status = status;
        this.createdAt = createdAt;
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
