package backend.repository;

import backend.domain.Job;
import backend.domain.JobStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.time.Instant;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestRepository {

    @Autowired
    private JobRepository jobRepository;

    @Test
    void createJobs() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("name", "Max Mustermann");
        jsonObjekt.put("alter", 30);
        jsonObjekt.put("aktiv", true);
        jsonObjekt.putPOJO("hobbys", new String[]{"Laufen", "Zocken"});

        Job job = new Job();
        job.setIdempotencyKey(UUID.randomUUID());
        job.setStatus(JobStatus.QUEUED);
        job.setPayload(mapper.writeValueAsString(jsonObjekt));
        job.setCreatedAt(Instant.now());
        job.setUpdatedAt(Instant.now());
        job.setAttempt_count(0);
        jobRepository.save(job);
    }
}
