package backend.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID idempotencyKey;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String payload;

    private Instant createdAt;

    private Instant updatedAt;

    private String claimed_by;

    private int attempt_count;

    private Instant lease_until;

    @OneToOne(mappedBy = "job")
    private JobResult result;

    public UUID getId() {
        return id;
    }

    public UUID getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(UUID idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getClaimed_by() {
        return claimed_by;
    }

    public void setClaimed_by(String claimed_by) {
        this.claimed_by = claimed_by;
    }

    public int getAttempt_count() {
        return attempt_count;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public JobResult getResult() {
        return result;
    }

    public void setResult(JobResult result) {
        this.result = result;
    }

    public void setAttempt_count(int attempt_count) {
        this.attempt_count = attempt_count;
    }

    public Instant getLease_until() {
        return lease_until;
    }

    public void setLease_until(Instant lease_until) {
        this.lease_until = lease_until;
    }
}
