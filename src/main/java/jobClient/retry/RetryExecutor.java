package jobClient.retry;

import java.util.concurrent.Callable;

public class RetryExecutor {

    private final RetryPolicy policy;

    public RetryExecutor(RetryPolicy policy){
        this.policy = policy;
    }

    public <T> T execute(Callable<T> callable) throws Exception{
        int attempt = 1;
        while (true){
            try {
                return callable.call();
            } catch (Exception e) {
                if(!policy.shouldRetry(e, attempt)){
                    throw e;
                }
                System.out.println("Last attempt failed with cause: " + e.getMessage());
                System.out.println("Waiting " + policy.nexDelay(attempt) + "ms for retry...");
                Thread.sleep(policy.nexDelay(attempt));
                attempt++;
                System.out.println("Retrying now in attempt: " + attempt);
            }
        }
    }
}
