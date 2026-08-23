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
                System.out.println("Starte Versuch: " + attempt + " für Aufruf: " + callable.getClass().getName());
                return callable.call();
            } catch (Exception e) {
                if(!policy.shouldRetry(e, attempt)){
                    System.out.println("Policy lehnt einen neuen Verarbeitungsversuch für Aufruf: " + callable.getClass().getName() + " ab.");
                    System.out.println("Letzter Versuch scheiterte an: " + e.getMessage());
                    throw e;
                }
                System.out.println("Warte " + policy.nextDelay(attempt) + "ms bis zum nächsten Retry...");
                Thread.sleep(policy.nextDelay(attempt));
                attempt++;
            }
        }
    }
}
