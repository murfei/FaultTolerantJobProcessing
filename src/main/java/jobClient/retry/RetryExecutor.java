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
                Thread.sleep(policy.nexDelay(attempt));
                attempt++;
            }
        }
    }
}
