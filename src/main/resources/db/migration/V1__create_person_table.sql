-- Create sequence for person primary key
CREATE SEQUENCE person_seq START WITH 1000;

-- Create person table
CREATE TABLE person
(
    id         BIGINT       NOT NULL DEFAULT nextval('person_seq'),
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(100) NOT NULL,
    email      VARCHAR(255),
    phone      VARCHAR(20),
    birth_date DATE,
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    CONSTRAINT pk_person PRIMARY KEY (id)
);

-- Create index for name search (FR-006)
CREATE INDEX idx_person_name ON person (last_name, first_name);
