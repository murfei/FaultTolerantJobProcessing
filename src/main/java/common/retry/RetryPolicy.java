package common.retry;

import java.time.Duration;

public abstract class RetryPolicy {

    protected final int maxAttempts;
    protected final Duration initialDelay;
    protected final double backoffFactor;

    public RetryPolicy(int maxAttempts, Duration initialDelay, double backoffFactor) {
        this.maxAttempts = maxAttempts;
        this.initialDelay = initialDelay;
        this.backoffFactor = backoffFactor;
    }

    public abstract boolean shouldRetry(Exception e, int attempt);

    public abstract long nextDelay(int attempt);
}
