package backend.repository;

import backend.domain.Job;
import backend.domain.JobStatus;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {

    Optional<Job> findByIdempotencyKey(String idempotencyKey);

    @NativeQuery(value = "SELECT * FROM jobs WHERE status = :status LIMIT :limit FOR UPDATE SKIP LOCKED")
    Optional<Job> findByStatus(JobStatus status, Limit limit);

    @Query("select j from Job j where j.status = :status and j.lease_until < :timestamp")
    List<Job> findByStatusAndLease_untilBefore(JobStatus status, Instant timestamp);
}
