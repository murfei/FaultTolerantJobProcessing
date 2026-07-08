package backend.Exception;

import backend.domain.Job;

public class IdempotencyException extends Exception {

    private final Job existingJob;

    public IdempotencyException(String message, Job existingJob) {
        super(message);
        this.existingJob = existingJob;
    }

    public IdempotencyException(Job existingJob) {
        super("Idempotenz-Key existiert bereits.");
        this.existingJob = existingJob;
    }

    public Job getExistingJob() {
        return existingJob;
    }
}
