package backend.worker;

import backend.domain.Job;
import backend.domain.JobResult;
import backend.recovery.DBRetryPolicy;
import backend.service.WorkerService;
import common.retry.RetryExecutor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class Worker implements Runnable {

    private final UUID id;
    private final WorkerService workerService;
    private final Processor processor;
    private final RetryExecutor retryExecutor;

    public Worker(WorkerService workerService, Processor processor) {
        this.id = UUID.randomUUID();
        this.workerService = workerService;
        this.processor = processor;
        this.retryExecutor = new RetryExecutor(new DBRetryPolicy());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Optional<Job> job = retryExecutor.execute(() -> workerService.claimNextJob(id));
                if (job.isEmpty()) {
                    System.out.println("Worker: " + id + " No job found");
                    if (!sleep(2000)) {
                        break;
                    }
                    continue;
                }
                System.out.println("Worker: " + id + " working on job: " + job.get().getIdempotencyKey());
                JobResult result = processor.process(job.get());
                retryExecutor.execute(() -> workerService.finishJob(job.get().getId(), id, result));
                System.out.println("Worker: " + id + " finished job: " + job.get().getIdempotencyKey());
            } catch (Exception e) {
                System.out.println("Worker: " + id + " failed with error: " + e.getMessage());//TODO: Logging Messages überall einheitlich auf deutsch oder englisch
            }
        }
    }

    private boolean sleep(long millis) {
        try {
            Thread.sleep(millis);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}
