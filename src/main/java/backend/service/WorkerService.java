package backend.service;

import backend.domain.Job;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WorkerService {

    public Optional<Job> claimNextJob(UUID workerId){
        return Optional.empty();
    }
}
