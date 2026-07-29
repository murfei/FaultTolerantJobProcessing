package backend.Exception;

public class LeaseExpiredException extends RuntimeException {
    public LeaseExpiredException(String message) {
        super(message);
    }

    public LeaseExpiredException() {
        super("Der Lease für diesen Job ist abgelaufen.");
    }
}
