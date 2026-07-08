package jobClient.retry;

public interface RetryPolicyInterface {

    boolean shouldRetry(Exception e, int attempt);

    long nexDelay(int attempt);
}
