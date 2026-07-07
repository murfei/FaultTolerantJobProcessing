ALTER TABLE job_result
DROP
COLUMN id;

ALTER TABLE job_result
    ADD CONSTRAINT pk_job_result PRIMARY KEY (job_id);