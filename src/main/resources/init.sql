CREATE TABLE IF NOT EXISTS specialties
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS developers(
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(64) NOT NULL,
    last_name VARCHAR(128) NOT NULL,
    status VARCHAR(64),
    specialty_id SERIAL,
    FOREIGN KEY(specialty_id) REFERENCES specialties(id)
);

CREATE TABLE IF NOT EXISTS skills
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS developer_skills
(
    developer_id SERIAL,
    skill_id SERIAL,
    FOREIGN KEY (developer_id) REFERENCES developers(id),
    FOREIGN KEY (skill_id) REFERENCES skills(id)
);


INSERT INTO developers (first_name, last_name)
VALUES ('Petr', 'Petrov');

-- checked request --

-- SELECT d.id, d.first_name, d.last_name, d.status, s.name as skill_name, sp.name as specialty_name
--                             FROM developers d
--                             LEFT JOIN developer_skills ds ON d.id = ds.developer_id
--                             LEFT JOIN skills s ON ds.skill_id = s.id
--                             LEFT JOIN specialties sp ON d.specialty_id = sp.id;
