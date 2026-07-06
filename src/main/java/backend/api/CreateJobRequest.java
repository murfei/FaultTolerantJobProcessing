package backend.api;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public class CreateJobRequest {

    private UUID idempotencyKey;

    @NotBlank
    private String payload;

    public CreateJobRequest(UUID idempotencyKey, String payload) {
        this.idempotencyKey = idempotencyKey;
        this.payload = payload;
    }

    public String getPayload() {
        return payload;
    }

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }
}
