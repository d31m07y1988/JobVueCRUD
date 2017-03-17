DROP SEQUENCE IF EXISTS companies_seq CASCADE;
DROP SEQUENCE IF EXISTS persons_seq CASCADE;
DROP SEQUENCE IF EXISTS job_seq CASCADE;

DROP TABLE IF EXISTS job;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS persons;

CREATE SEQUENCE companies_seq START 1;
CREATE TABLE companies
(
  id         INT4 PRIMARY KEY DEFAULT nextval('companies_seq'),
  name       VARCHAR NOT NULL,
  inn      VARCHAR NOT NULL
);
CREATE UNIQUE INDEX companies_unique_inn_idx ON companies (inn);

CREATE SEQUENCE persons_seq START 1;
CREATE TABLE persons
(
  id         INT4 PRIMARY KEY DEFAULT nextval('persons_seq'),
  fullname       VARCHAR NOT NULL,
  phone      VARCHAR NOT NULL,
  email      VARCHAR NOT NULL
);
CREATE UNIQUE INDEX persons_unique_email_idx ON persons (email);

CREATE SEQUENCE job_seq START 1;
CREATE TABLE job
(
  id         INT4 PRIMARY KEY DEFAULT nextval('job_seq'),
  persons_id         INT4 NOT NULL,
  companies_id         INT4 NOT NULL,
  date_start       DATE NOT NULL,
  date_end      DATE NOT NULL,
  manager      BOOL NOT NULL,
  salary      NUMERIC NOT NULL,
  FOREIGN KEY (companies_id) REFERENCES companies (id) ON DELETE CASCADE,
  FOREIGN KEY (persons_id) REFERENCES persons (id) ON DELETE CASCADE
);