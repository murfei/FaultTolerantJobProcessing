package backend.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CreateJobRequest {

    @NotNull
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
