package common.retry;

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
                System.out.println("Starting attempt: " + attempt + " for callable: " + callable.getClass().getName());
                return callable.call();
            } catch (Exception e) {
                if(!policy.shouldRetry(e, attempt)){
                    System.out.println("Policy decided not to retry call of callable: " + callable.getClass().getName());
                    System.out.println("Last attempt failed with cause: " + e.getMessage());
                    throw e;
                }
                System.out.println("Waiting " + policy.nextDelay(attempt) + "ms for retry...");
                Thread.sleep(policy.nextDelay(attempt));
                attempt++;
            }
        }
    }
}
