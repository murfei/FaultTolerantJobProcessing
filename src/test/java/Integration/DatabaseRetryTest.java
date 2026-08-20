package Integration;

import backend.Application;
import backend.domain.Job;
import com.zaxxer.hikari.HikariDataSource;
import jobClient.dto.CreateJobRequest;
import jobClient.dto.CreateJobResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.ToxiproxyContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
public class DatabaseRetryTest {

    static final Network network = Network.newNetwork();

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("jobprocessing")
                    .withUsername("myuser")
                    .withPassword("secret")
                    .withNetwork(network)
                    .withNetworkAliases("postgres");

    @Container
    static ToxiproxyContainer toxiproxy =
            new ToxiproxyContainer("ghcr.io/shopify/toxiproxy:2.9.0")
                    .withNetwork(network);

    static ToxiproxyContainer.ContainerProxy dbProxy;

    @BeforeAll
    static void setupProxy() {
        dbProxy = toxiproxy.getProxy(postgres, 5432);
    }

    @DynamicPropertySource
    static void configureDatabase(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.hikari.connection-timeout", () -> "1000");
        registry.add("spring.datasource.url", () ->
                "jdbc:postgresql://" + toxiproxy.getHost() + ":" + dbProxy.getProxyPort() + "/jobprocessing");
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    DataSource dataSource;

    @Test
    void jobCreationRecoversAfterTransientDbOutage(){
        UUID idempotencyKey = UUID.randomUUID();
        CreateJobRequest request = new CreateJobRequest(idempotencyKey, "{\"name\":\"Test\"}");
        // DB-Verbindung unterbrechen
        dbProxy.setConnectionCut(true);
        // erzwingt Neuaufbau beim nächsten Zugriff, da sonst eine andere Verbindung aus dem Connection Pool genommen werden kann
        if (dataSource instanceof HikariDataSource hikari) {
            hikari.getHikariPoolMXBean().softEvictConnections();
        }
        // DB-Verbindung wiederherstellen
        // Asynchron, da die Wiederherstellung erst nach dem ersten Zugriffsversuch geschehen darf
        CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(4000);
                dbProxy.setConnectionCut(false);
            } catch (InterruptedException ignored) {}
        });
        //eigentlicher create-Aufruf der Rest-API
        ResponseEntity<CreateJobResponse> response =
                restTemplate.postForEntity("/api/jobs/job", request, CreateJobResponse.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        ResponseEntity<Job> jobResponse = restTemplate.getForEntity("/api/jobs/job/{id}", Job.class, idempotencyKey);
        assertEquals(idempotencyKey, jobResponse.getBody().getIdempotencyKey());
    }

    @Test
    void jobCreationFailsAfterExhaustedRetriesOnPermanentOutage() {
        CreateJobRequest request = new CreateJobRequest(UUID.randomUUID(), "{\"name\":\"Test\"}");

        dbProxy.setConnectionCut(true); // Deaktivieren der DB-Verbindung ohne folgende Wiederherstellung
        if (dataSource instanceof HikariDataSource hikari) {
            hikari.getHikariPoolMXBean().softEvictConnections();
        }
        ResponseEntity<CreateJobResponse> response =
                restTemplate.postForEntity("/api/jobs/job", request, CreateJobResponse.class);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

        dbProxy.setConnectionCut(false); // aufräumen für nachfolgende Tests
    }

    //TODO: testRetryNotAppliedOnConstraintViolatio
}