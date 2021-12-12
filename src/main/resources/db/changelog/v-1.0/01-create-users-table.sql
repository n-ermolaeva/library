--Создание таблицы Пользователи
CREATE TABLE users
(
    id       SERIAL,
    username VARCHAR(255),
    password VARCHAR(255),
    CONSTRAINT pk_user PRIMARY KEY (id)
);
INSERT INTO users (username, password)
VALUES ('admin','admin'),
('user','user')