package backend.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID idempotencyKey;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

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
