ALTER TABLE jobs
DROP
COLUMN claimed_by;

ALTER TABLE jobs
    ADD claimed_by UUID;

ALTER TABLE jobs
    ALTER COLUMN created_at SET NOT NULL;

ALTER TABLE jobs
    ALTER COLUMN updated_at SET NOT NULL;