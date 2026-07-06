package backend.worker;

import backend.domain.Job;
import backend.domain.JobResult;
import org.springframework.stereotype.Component;

@Component
public class JobProcessor implements Processor {
    @Override
    public JobResult process(Job job) {
        return null; //TODO: Fachlogik
    }
}
