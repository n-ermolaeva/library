create function get_books_by_name_autors(sometext text)
    returns table (
        book_name varchar,
        author_name varchar
        )
    language plpgsql
as
$$
begin
return query (
    select b.book_name, a.author_name from Books b join Authors a
                        on b.author_books_id = a.author_id
						WHERE a.author_name ILIKE sometext
    );
IF NOT FOUND THEN
        RAISE EXCEPTION 'Нет книг по заданной части имени автора: %.', sometext;
END IF;
end;
$$;