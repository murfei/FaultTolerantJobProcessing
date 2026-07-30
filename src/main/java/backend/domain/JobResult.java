package backend.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "job_result")
public class JobResult {

    @Id
    private UUID jobId;
//TODO: ist das hier richtig mit doppelter id oder so? einmal genau prüfen was hier jobId macht und was job und woher im schema id kommt
    @MapsId
    @OneToOne
    @JoinColumn(name = "job_id", nullable = false, unique = true)
    private Job job;

    @JdbcTypeCode(SqlTypes.JSON)
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
