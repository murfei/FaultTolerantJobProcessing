package backend.recovery;

import common.retry.RetryPolicy;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.Duration;

public class DBRetryPolicy implements RetryPolicy {

    private final int maxAttempts;
    private final Duration initialDelay;
    private final double backoffFactor;

    public DBRetryPolicy() {
        this.maxAttempts = 5;
        this.initialDelay = Duration.ofMillis(200);
        this.backoffFactor = 2;
    }

    @Override
    public boolean shouldRetry(Exception e, int attempt) {
        if (attempt >= maxAttempts || e instanceof DataIntegrityViolationException) {
            return false;
        }

        return e instanceof DataAccessException;
    }

    @Override
    public long nextDelay(int attempt) {
        return (long) (initialDelay.toMillis()
                * Math.pow(backoffFactor, attempt - 1));
    }
}
