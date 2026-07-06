package backend.service;

import backend.domain.Job;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerService {

    public Optional<Job> claimNextJob(){
        return Optional.empty();
    }
}
