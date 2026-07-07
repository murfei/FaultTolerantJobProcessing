package backend.worker;

import backend.domain.Job;
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
                    Thread.sleep(1000);
                    continue;
                }
                processor.process(job.get());
            } catch (InterruptedException e) { //TODO: Fehlerbehandlung
                System.out.println("Worker interrupted");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
