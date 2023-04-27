DROP TABLE IF EXISTS developer;
CREATE TABLE developer
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR,
    last_name  VARCHAR,
    skill      VARCHAR,
    specialty  VARCHAR
);
INSERT INTO developer (first_name, last_name, skill, specialty)
VALUES ('Ivan', 'Ivanov', 'Java', 'Development');
