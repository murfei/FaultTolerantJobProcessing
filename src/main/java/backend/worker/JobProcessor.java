package backend.worker;

import backend.domain.Job;
import backend.domain.JobResult;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

@Component
public class JobProcessor implements Processor {
    @Override
    public JobResult process(Job job) throws Exception {
        Thread.sleep(10000);
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("result", "Testergebnis");
        return new JobResult(job, mapper.writeValueAsString(jsonObjekt)); //TODO: Fachlogik
    }
}
