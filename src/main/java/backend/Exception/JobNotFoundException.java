package backend.Exception;

public class JobNotFoundException extends Exception {
    public JobNotFoundException(String message) {
        super(message);
    }

    public JobNotFoundException() {
        super("Der Job konnte nicht gefunden werden.");
    }
}
