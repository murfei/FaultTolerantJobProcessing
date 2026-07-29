package backend.repository;

import backend.domain.JobResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JobResultRepository extends JpaRepository<JobResult, UUID> {
}
