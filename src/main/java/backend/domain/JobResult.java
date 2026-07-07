package backend.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "job_result")
public class JobResult {

    @Id
    private UUID jobId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "job_id", nullable = false, unique = true)
    private Job job;

    @Column(columnDefinition = "jsonb")
    private String result;

    private Instant createdAt;

    public JobResult() {
    }

    public JobResult(Job job, String result) {
        this.job = job;
        this.result = result;
        this.createdAt = Instant.now();
    }

    public String getResult() {
        return result;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
