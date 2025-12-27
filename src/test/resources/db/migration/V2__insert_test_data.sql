INSERT INTO person (id, first_name, last_name, email, phone, birth_date, created_at, updated_at)
VALUES
    (nextval('person_seq'), 'John', 'Doe', 'john.doe@example.com', '+1-555-0101', '1985-03-15', now(), now()),
    (nextval('person_seq'), 'Jane', 'Smith', 'jane.smith@example.com', '+1-555-0102', '1990-07-22', now(), now()),
    (nextval('person_seq'), 'Michael', 'Johnson', 'm.johnson@example.com', NULL, '1978-11-08', now(), now()),
    (nextval('person_seq'), 'Emily', 'Williams', 'emily.w@example.com', '+1-555-0104', NULL, now(), now()),
    (nextval('person_seq'), 'David', 'Brown', NULL, '+1-555-0105', '1982-05-30', now(), now()),
    (nextval('person_seq'), 'Sarah', 'Miller', 'sarah.m@example.com', '+1-555-0106', '1995-01-17', now(), now()),
    (nextval('person_seq'), 'Robert', 'Davis', 'r.davis@example.com', NULL, NULL, now(), now()),
    (nextval('person_seq'), 'Lisa', 'Garcia', 'lisa.g@example.com', '+1-555-0108', '1988-09-03', now(), now()),
    (nextval('person_seq'), 'James', 'Wilson', NULL, NULL, '1975-12-25', now(), now()),
    (nextval('person_seq'), 'Anna', 'Martinez', 'anna.m@example.com', '+1-555-0110', '1992-04-10', now(), now());
