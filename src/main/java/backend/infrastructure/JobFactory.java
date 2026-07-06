package backend.infrastructure;

import backend.api.CreateJobRequest;
import backend.domain.Job;
import backend.domain.JobStatus;

import java.time.Instant;

public class JobFactory {
    public static Job createJob(CreateJobRequest request) {
        Job job = new Job();
        job.setIdempotencyKey(request.getIdempotencyKey());
        job.setPayload(request.getPayload());
        job.setStatus(JobStatus.QUEUED);
        Instant now = Instant.now();
        job.setCreatedAt(now);
        job.setUpdatedAt(now);
        job.setAttempt_count(0);

        return job;
    }
}
