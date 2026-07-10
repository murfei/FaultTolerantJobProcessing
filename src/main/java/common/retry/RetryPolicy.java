package common.retry;

public interface RetryPolicy {

    boolean shouldRetry(Exception e, int attempt);

    long nextDelay(int attempt);
}
