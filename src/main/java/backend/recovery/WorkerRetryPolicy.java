package backend.recovery;

import common.retry.RetryPolicy;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.CannotSerializeTransactionException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import java.time.Duration;

public class WorkerRetryPolicy implements RetryPolicy {

        private final int maxAttempts;
        private final Duration initialDelay;
        private final double backoffFactor;

        public WorkerRetryPolicy(int maxAttempts, Duration initialDelay, double backoffFactor) {
            this.maxAttempts = maxAttempts;
            this.initialDelay = initialDelay;
            this.backoffFactor = backoffFactor;
        }

        @Override
        public boolean shouldRetry(Exception e, int attempt) {
            if (attempt >= maxAttempts) {
                return false;
            }

            return e instanceof CannotGetJdbcConnectionException
                    || e instanceof CannotAcquireLockException
                    || e instanceof CannotSerializeTransactionException
                    || e instanceof DeadlockLoserDataAccessException
                    || e instanceof DataAccessException;
        }

        @Override
        public long nextDelay(int attempt) {
            return (long) (initialDelay.toMillis()
                    * Math.pow(backoffFactor, attempt - 1));
        }
    }
