package Integration;

import backend.Application;
import backend.api.CreateJobRequest;
import backend.domain.Job;
import backend.domain.JobStatus;
import backend.service.JobService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(classes = Application.class, properties = "worker.count=0")
@ActiveProfiles("test")
@DirtiesContext
public class JobRecoveryForWorkerCrashTest {

    @Container
    static PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:17")
                    .withDatabaseName("jobprocessing")
                    .withUsername("myuser")
                    .withPassword("secret");

    @DynamicPropertySource
    static void configureDatabase(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    JobService jobService;

    Process worker1;
    Process worker2;

    @AfterEach
    void cleanup() {
        // Aufräumen, falls ein Assert vorher fehlschlägt und die Prozesse noch leben
        if (worker1 != null && worker1.isAlive()) worker1.destroyForcibly();
        if (worker2 != null && worker2.isAlive()) worker2.destroyForcibly();
    }

    @Test
    void jobSurvivesRealWorkerProcessCrash() throws Exception {
        Path jarPath = locateJar();
        UUID idempotencyKey = UUID.randomUUID();

        // Worker Prozess starten
        worker1 = startInstance(jarPath);
        System.out.println("Worker1 gestartet");
        Job job = jobService.createJob(new CreateJobRequest(idempotencyKey, "{\"payload\":\"Test\"}"));
        assertEquals(JobStatus.QUEUED, job.getStatus());
        System.out.println("Job erstellt");

        // Warten, dass worker1 den Job beansprucht hat
        await().atMost(Duration.ofSeconds(15))
                .pollInterval(Duration.ofMillis(200))
                .until(() -> jobService.getJobById(idempotencyKey)
                        .map(j -> j.getStatus() == JobStatus.RUNNING)
                        .orElse(false));
        System.out.println("Job beansprucht durch Worker mit ID: " + jobService.getJobById(idempotencyKey).get().getClaimed_by());

        // Harter Kill des Prozesses
        worker1.destroyForcibly();
        System.out.println("Worker1 abgebrochen");
        assertTrue(worker1.waitFor(10, TimeUnit.SECONDS), "Prozess wurde nicht rechtzeitig beendet");

        // Kontrolle, dass Job tatsächlich Running ist
        Job orphaned = jobService.getJobById(idempotencyKey).orElseThrow();
        assertEquals(JobStatus.RUNNING, orphaned.getStatus());
        System.out.println("Status des von Worker1 bearbeiteten Jobs: " + orphaned.getStatus());

        // Neuen Worker starten
        worker2 = startInstance(jarPath);
        System.out.println("Worker2 gestartet");

        // Warten, dass worker2 den Job abgeschlossen hat
        await().atMost(Duration.ofSeconds(90))
                .pollInterval(Duration.ofSeconds(1))
                .until(() -> jobService.getJobById(idempotencyKey)
                        .map(j -> j.getStatus() == JobStatus.SUCCEEDED)
                        .orElse(false));
        System.out.println("Job erfolgreich abgeschlossen");

        Job finished = jobService.getJobById(idempotencyKey).orElseThrow();
        assertEquals(2, finished.getAttempt_count());
        assertTrue(finished.getResult().getResult().contains("{\"result\": \"Testergebnis\", \"Zeitpunkt\": "));
        System.out.println(finished.getStatus());
        System.out.println(finished.getResult().getResult());
    }

    private Process startInstance(Path jarPath) throws IOException {
        return new ProcessBuilder(
                "java", "-jar", jarPath.toString(),
                "--server.port=0",
                "--spring.datasource.url=" + postgres.getJdbcUrl(),
                "--spring.datasource.username=" + postgres.getUsername(),
                "--spring.datasource.password=" + postgres.getPassword())
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .redirectError(ProcessBuilder.Redirect.INHERIT)
                .start();
    }

    private Path locateJar() throws IOException {
        try (Stream<Path> files = Files.list(Path.of("target"))) {
            return files
                    .filter(p -> p.toString().endsWith(".jar"))
                    .filter(p -> !p.toString().contains("original")) // Spring-Boot-Repackage-Artefakt
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException(
                            "Kein Jar in target/ gefunden -- vorher `mvn package` ausfuehren"));
        }
    }
}