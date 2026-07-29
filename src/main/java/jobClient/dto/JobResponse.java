package jobClient.dto;

import backend.domain.JobResult;
import backend.domain.JobStatus;

import java.time.Instant;
import java.util.UUID;

public class JobResponse {

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

    @Override
    public String toString() {
        return "JobResponse [idempotencyKey=" + idempotencyKey + ", status=" + status + ", payload=" + payload
                + ", createdAt=" + createdAt + ", result=" + result + "]";
    }
}
