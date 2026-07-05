CREATE TABLE job_result
(
    id         UUID NOT NULL,
    job_id     UUID NOT NULL,
    result     JSONB,
    created_at TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_job_result PRIMARY KEY (id)
);

ALTER TABLE job_result
    ADD CONSTRAINT uc_job_result_job UNIQUE (job_id);

ALTER TABLE job_result
    ADD CONSTRAINT FK_JOB_RESULT_ON_JOB FOREIGN KEY (job_id) REFERENCES jobs (id);