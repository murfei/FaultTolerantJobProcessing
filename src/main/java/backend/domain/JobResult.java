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

    //Erzeugt keine 2. ID, sondern sorgt dafür, dass der Primary-Key (jobId) gleichzeitig ein Foreign-Key auf die ID des Jobs ist
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

    public UUID getJobId() {
        return jobId;
    }

    public String getResult() {
        return result;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
