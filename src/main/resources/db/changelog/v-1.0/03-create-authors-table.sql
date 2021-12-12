--Создание таблицы Авторы
create table authors
(
    author_id SERIAL,
    author_name CHARACTER VARYING(70),
    CONSTRAINT authors_pk PRIMARY KEY (author_id)
);
INSERT INTO authors (author_name)
VALUES ('Tolstoy'),
       ('Dostoevsky'),
       ('Bulgakov')

