package backend.worker;

import backend.domain.Job;
import backend.domain.JobResult;
import backend.service.WorkerService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class Worker implements Runnable {

    private final UUID id;
    private final WorkerService workerService;
    private final Processor processor;

    public Worker(WorkerService workerService, Processor processor) {
        this.id = UUID.randomUUID();
        this.workerService = workerService;
        this.processor = processor;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Optional<Job> job = workerService.claimNextJob(id);
            try {
                if (job.isEmpty()) {
                    System.out.println("Thread: " + Thread.currentThread().threadId()+ " No job found");
                    Thread.sleep(2000);
                    continue;
                }
                System.out.println("Thread: " + Thread.currentThread().threadId()+ " working on job: " + job.get().getId());
                JobResult result = processor.process(job.get());       //TODO: Überlegen ob Heartbeat sinnvoll ist, oder zumindest als Alternative in Paper erwähnen
                workerService.finishJob(job.get().getId(), id, result);
                System.out.println("Thread: " + Thread.currentThread().threadId()+ " finished job: " + job.get().getId());
            } catch (InterruptedException e) { //TODO: Fehlerbehandlung
                System.out.println("Worker interrupted");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
