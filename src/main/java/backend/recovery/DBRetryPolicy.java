package backend.recovery;

import common.retry.RetryPolicy;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.CannotCreateTransactionException;

import java.time.Duration;

public class DBRetryPolicy extends RetryPolicy {

    public DBRetryPolicy() {
        super(5, Duration.ofMillis(200), 2);
    }

    @Override
    public boolean shouldRetry(Exception e, int attempt) {
        if (attempt >= maxAttempts || e instanceof DataIntegrityViolationException) {
            return false;
        }
        return e instanceof DataAccessException || e instanceof CannotCreateTransactionException;
    }

    @Override
    public long nextDelay(int attempt) {
        return (long) (initialDelay.toMillis()
                * Math.pow(backoffFactor, attempt - 1));
    }
}
