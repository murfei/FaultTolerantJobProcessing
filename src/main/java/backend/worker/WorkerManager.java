package backend.worker;

import backend.service.WorkerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WorkerManager {

    private int workerCount;

    private final ExecutorService executorService;
    private final WorkerService workerService;
    private final Processor jobProcessor;

    public WorkerManager(WorkerService workerService, Processor jobProcessor, @Value("${worker.count}") int workerCount) {
        this.workerService = workerService;
        this.jobProcessor = jobProcessor;
        this.workerCount = workerCount;

        this.executorService = Executors.newFixedThreadPool(workerCount);
    }

    @PostConstruct
    void start(){
        for(int i = 0; i < workerCount; i++){
            executorService.submit(new Worker(workerService, jobProcessor));
        }
    }
}
