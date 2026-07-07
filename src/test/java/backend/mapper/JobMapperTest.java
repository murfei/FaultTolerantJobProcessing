package backend.mapper;

import backend.api.JobDto;
import backend.domain.Job;
import backend.domain.JobResult;
import backend.domain.JobStatus;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.UUID;

import static backend.mapper.JobMapper.toDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JobMapperTest {

    @Test
    void toDTOTest(){
        Job job = new Job();
        job.setIdempotencyKey(UUID.randomUUID());
        job.setStatus(JobStatus.QUEUED);
        job.setPayload("payload");
        job.setCreatedAt(Instant.now());
        job.setUpdatedAt(Instant.now());
        job.setAttempt_count(0);
        job.setLease_until(Instant.now());
        job.setResult(new JobResult());

        JobDto dto = toDTO(job);
        assertEquals(dto.getIdempotencyKey(), job.getIdempotencyKey());
        assertEquals(dto.getStatus(), job.getStatus());
        assertEquals(dto.getPayload(), job.getPayload());
        assertEquals(dto.getCreatedAt(), job.getCreatedAt());
        assertEquals(dto.getResult(), job.getResult());
    }
}

