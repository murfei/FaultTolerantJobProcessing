package jobClient.retry;

import jobClient.exception.RetryableHttpException;
import org.springframework.web.client.ResourceAccessException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.time.Duration;

public class RetryPolicy implements RetryPolicyInterface {

    private final int maxAttempts;
    private final Duration initialDelay;
    private final double backoffFactor;

    public RetryPolicy(int maxAttempts, Duration initialDelay, double backoffFactor) {
        this.maxAttempts = maxAttempts;
        this.initialDelay = initialDelay;
        this.backoffFactor = backoffFactor;
    }

    @Override
    public boolean shouldRetry(Exception e, int attempt) {
        if (attempt >= maxAttempts) {
            return false;
        }
        return e instanceof RetryableHttpException
                || e instanceof ConnectException
                || e instanceof SocketTimeoutException
                || e instanceof ResourceAccessException;
    }

    @Override
    public long nexDelay(int attempt) {
        return (long) (initialDelay.toMillis() * Math.pow(backoffFactor, attempt-1));
    }

}
