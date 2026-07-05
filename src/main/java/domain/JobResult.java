package domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "job_result")
public class JobResult {

    @Id
    private UUID id;

    @OneToOne
    @JoinColumn(name = "job_id", nullable = false, unique = true)
    private Job job;

    @Column(columnDefinition = "jsonb")
    private String result;

    private Instant createdAt;
}
