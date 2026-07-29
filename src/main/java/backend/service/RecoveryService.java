package backend.service;

import backend.domain.JobStatus;
import backend.recovery.RecoveryExecutor;
import backend.repository.JobRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RecoveryService {

    private final JobRepository repository;
    private final RecoveryExecutor recoveryExecutor;

    public RecoveryService(JobRepository repository, RecoveryExecutor recoveryExecutor) {
        this.repository = repository;
        this.recoveryExecutor = recoveryExecutor;
    }

    @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
    public void recoverCycle() {
        List<UUID> ids = repository.findByStatusAndLease_untilBefore(JobStatus.RUNNING, Instant.now());
        for (UUID id : ids) {
            recoveryExecutor.recoverJob(id);
        }
    }
}
