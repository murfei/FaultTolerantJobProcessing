package jobClient.exception;

import org.springframework.http.HttpStatusCode;

public class NonRetryableHttpException extends RuntimeException {
    public NonRetryableHttpException(String message) {
        super(message);
    }

    public NonRetryableHttpException(HttpStatusCode status, String message){
        super(status.toString() + ": " +message);
    }
}
