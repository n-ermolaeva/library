--Создание таблицы Книги
create table books
(
    book_id SERIAL PRIMARY KEY,
    book_name CHARACTER VARYING(70) not null,
    author_books_id INTEGER REFERENCES authors (author_id)
);
    INSERT INTO books (book_name, author_books_id)
    VALUES ('War and peace', 1),
           ('Idiot', 2),
           ('The Master and Margarita',3)
