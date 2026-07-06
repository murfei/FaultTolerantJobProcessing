package backend.worker;

import backend.domain.Job;
import backend.domain.JobResult;

public interface Processor {

    JobResult process(Job job);
}
