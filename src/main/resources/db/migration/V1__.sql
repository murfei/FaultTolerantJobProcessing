CREATE TABLE jobs
(
    id              UUID    NOT NULL,
    idempotency_key UUID    NOT NULL,
    status          VARCHAR(255),
    payload         JSONB,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    updated_at      TIMESTAMP WITHOUT TIME ZONE,
    claimed_by      VARCHAR(255),
    attempt_count   INTEGER NOT NULL,
    lease_until     TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_jobs PRIMARY KEY (id)
);

ALTER TABLE jobs
    ADD CONSTRAINT uc_jobs_idempotencykey UNIQUE (idempotency_key);