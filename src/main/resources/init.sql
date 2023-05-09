CREATE TABLE IF NOT EXISTS developers(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    status VARCHAR(64),
    specialty_id SERIAL,
    FOREIGN KEY(specialty_id) REFERENCES specialties(id)
);

CREATE TABLE IF NOT EXISTS developer_skills
(
    developer_id SERIAL,
    skill_id SERIAL,
    FOREIGN KEY (developer_id) REFERENCES developers(id),
    FOREIGN KEY (skill_id) REFERENCES skills(id)
);

CREATE TABLE IF NOT EXISTS skills
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS specialties
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);


INSERT INTO developers (first_name, last_name)
VALUES ('Petr', 'Petrov');

--DROP TABLE IF EXISTS specialties CASCADE ;--
