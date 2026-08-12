package backend.worker;

import backend.domain.Job;
import backend.domain.JobResult;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.Instant;

@Component
public class JobProcessor implements Processor {
    @Override
    public JobResult process(Job job) throws Exception {
        simulateProcessing(5_000);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("result", "Testergebnis");
        jsonObjekt.put("Zeitpunkt", Instant.now().toString());
        return new JobResult(job, mapper.writeValueAsString(jsonObjekt));
    }

    private void simulateProcessing(int millis) throws InterruptedException{
        Thread.sleep(millis);
    }
}
