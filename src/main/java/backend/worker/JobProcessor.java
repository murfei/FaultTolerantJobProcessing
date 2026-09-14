package backend.worker;

import backend.domain.Job;
import backend.domain.JobResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.Instant;

@Component
public class JobProcessor implements Processor {

    private final int processingTime;
    private final ObjectMapper mapper;

    public JobProcessor(@Value("${job.processing-time}") int processingTime) {
        this.processingTime = processingTime;
        mapper = new ObjectMapper();
    }

    @Override
    public JobResult process(Job job) throws Exception {
        simulateProcessing();

        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("result", "Testergebnis");
        jsonObjekt.put("Zeitpunkt", Instant.now().toString());
        return new JobResult(job, mapper.writeValueAsString(jsonObjekt));
    }

    private void simulateProcessing() throws InterruptedException {
        Thread.sleep(processingTime);
    }
}
