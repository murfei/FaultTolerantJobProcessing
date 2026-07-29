package backend.api;

import backend.domain.JobResult;
import backend.domain.JobStatus;

import java.time.Instant;
import java.util.UUID;

public class JobDto {

    private UUID idempotencyKey;

    private JobStatus status;

    private String payload;

    private Instant createdAt;

    private JobResult result;

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public JobStatus getStatus() {
        return status;
    }

    public String getPayload() {
        return payload;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public JobResult getResult() {
        return result;
    }

    public void setIdempotencyKey(UUID idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setResult(JobResult result) {
        this.result = result;
    }
}
