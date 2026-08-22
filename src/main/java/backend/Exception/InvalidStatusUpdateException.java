package backend.Exception;

public class InvalidStatusUpdateException extends RuntimeException {
    public InvalidStatusUpdateException(String message) {
        super(message);
    }

    public InvalidStatusUpdateException() {
        super("Es ist kein Statusübergang aus diesem Zustand heraus erlaubt");
    }
}
