package backend.infrastructure;

import backend.api.JobDto;
import backend.domain.Job;

public class JobMapper {

    public static JobDto toDTO(Job job) {
        JobDto dto = new JobDto();
        dto.setIdempotencyKey(job.getIdempotencyKey());
        dto.setStatus(job.getStatus());
        dto.setPayload(job.getPayload());
        dto.setCreatedAt(job.getCreatedAt());
        dto.setResult(job.getResult());
        return dto;
    }
}
