package backend.api;

import backend.domain.Job;
import backend.infrastructure.JobFactory;
import backend.service.JobService;
import backend.service.PayloadValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.node.ObjectNode;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JobControllerRetryTest {

    private static CreateJobRequest request;

    @BeforeAll
    static void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonObjekt = mapper.createObjectNode();
        jsonObjekt.put("name", "Max Mustermann");
        jsonObjekt.put("alter", 30);
        jsonObjekt.put("aktiv", true);
        jsonObjekt.putPOJO("hobbys", new String[]{"Laufen", "Zocken"});
        request = new CreateJobRequest(UUID.randomUUID(), mapper.writeValueAsString(jsonObjekt));
    }

    @Test
    void testRetryToManyAttempts() {
        JobService jobService = Mockito.mock(JobService.class);
        when(jobService.createJob(any(CreateJobRequest.class)))
                .thenThrow(new DataAccessResourceFailureException("DB nicht erreichbar"))
                .thenThrow(new DataAccessResourceFailureException("DB nicht erreichbar"))
                .thenThrow(new DataAccessResourceFailureException("DB nicht erreichbar"))
                .thenThrow(new DataAccessResourceFailureException("DB nicht erreichbar"))
                .thenThrow(new DataAccessResourceFailureException("DB nicht erreichbar"))
                .thenThrow(new DataAccessResourceFailureException("DB nicht erreichbar"));
        JobController tempController = new JobController(jobService, new PayloadValidator());

        ResponseEntity<CreateJobResponse> response = tempController.createJob(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(jobService, times(5))
                .createJob(any(CreateJobRequest.class));
    }

    @Test
    void testRetryNonRetriableRequest() {
        Job responseJob = JobFactory.createJob(request);
        SQLException sqlException =
                new SQLException("duplicate idempotency key", "23505");

        DataIntegrityViolationException integrityException =
                new DataIntegrityViolationException(
                        "duplicate idempotency key",
                        sqlException
                );

        JobService jobService = Mockito.mock(JobService.class);
        when(jobService.createJob(any(CreateJobRequest.class)))
                .thenThrow(new NullPointerException())
                .thenThrow(integrityException);
        when(jobService.getJobById(request.getIdempotencyKey()))
                .thenReturn(Optional.of(responseJob));
        JobController tempController = new JobController(jobService, new PayloadValidator());

        //Unklassifizierter Fehler
        ResponseEntity<CreateJobResponse> response = tempController.createJob(request);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        verify(jobService, times(1))
                .createJob(any(CreateJobRequest.class));

        //Idempotenzfall
        response = tempController.createJob(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseJob.getIdempotencyKey(), response.getBody().getIdempotencyKey());
        verify(jobService, times(2))
                .createJob(any(CreateJobRequest.class));
    }

    @Test
    void testRetrySuccessfulRetry(){
        JobService jobService = Mockito.mock(JobService.class);
        Job responseJob = JobFactory.createJob(request);
        when(jobService.createJob(any(CreateJobRequest.class)))
                .thenThrow(new DataAccessResourceFailureException("DB nicht erreichbar"))
                .thenThrow(new DataAccessResourceFailureException("DB nicht erreichbar"))
                .thenThrow(new DataAccessResourceFailureException("DB nicht erreichbar"))
                .thenReturn(responseJob);
        JobController tempController = new JobController(jobService, new PayloadValidator());

        ResponseEntity<CreateJobResponse> response = tempController.createJob(request);
        assertEquals(responseJob.getIdempotencyKey(), response.getBody().getIdempotencyKey());
        verify(jobService, times(4))
                .createJob(any(CreateJobRequest.class));
    }
}
