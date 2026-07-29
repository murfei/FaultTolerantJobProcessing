package jobClient.exception;

import org.springframework.http.HttpStatusCode;

public class RetryableHttpException extends RuntimeException {
    public RetryableHttpException(String message) {
        super(message);
    }

    public RetryableHttpException(HttpStatusCode status, String message){
        super(status.toString() + ": " +message);
    }
}
