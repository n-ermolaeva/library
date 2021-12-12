CREATE TABLE role
(
    id   SERIAL,
    name VARCHAR(255),
    CONSTRAINT pk_role PRIMARY KEY (id)
);
INSERT INTO role (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN')