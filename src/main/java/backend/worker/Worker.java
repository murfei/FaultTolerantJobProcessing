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
                    System.out.println("Thread: " + Thread.currentThread().threadId() + " No job found");
                    if (!sleep(2000)) {
                        break;
                    }
                    continue;
                }
                System.out.println("Thread: " + Thread.currentThread().threadId() + " working on job: " + job.get().getId());
                JobResult result = processor.process(job.get());       //TODO: Überlegen ob Heartbeat sinnvoll ist, oder zumindest als Alternative in Paper erwähnen
                retryExecutor.execute(() -> workerService.finishJob(job.get().getId(), id, result));
                System.out.println("Thread: " + Thread.currentThread().threadId() + " finished job: " + job.get().getId());
            } catch (Exception e) {
                e.printStackTrace();
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
