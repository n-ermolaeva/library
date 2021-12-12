package com.library.service;

import com.dto.books.DescriptionBooksDto;
import com.library.exception.AuthorNotFoundException;
import com.library.exception.BookNotFoundException;
import com.library.models.Books;
import com.library.repositories.AuthorsRepository;
import com.library.repositories.BooksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class BooksService {

    private final BooksRepository booksRepository;
    private final AuthorsRepository authorsRepository;

    @PersistenceContext()
    private EntityManager entityManager;

    public void addBooks(Integer bookId,
                         String bookName,
                         Integer authorBooksId) throws AuthorNotFoundException {
        if (authorsRepository.findAllBy()
                .stream().noneMatch(author -> author.getAuthor_id().equals(authorBooksId))) {
            throw new AuthorNotFoundException("Не найден автор с ID " + authorBooksId + " добавляемой книги");
        }
        booksRepository.save(new Books(bookId, bookName, authorBooksId));
    }

    public List<DescriptionBooksDto> booksList() {
        return booksRepository.findAllBy()
                .stream()
                .map(books -> new DescriptionBooksDto().withBookId(books.getBook_id())
                        .withBookName(books.getBook_name())
                        .withAuthorBooksId(books.getAuthor_books_id())
                )
                .collect(Collectors.toList());
    }

    public Optional<DescriptionBooksDto> findBookById(Integer id) throws Throwable {
        List<Books> booksList = booksRepository.findAllBy();
        Optional<DescriptionBooksDto> optionalDescriptionBooksDto = booksList
                .stream()
                .filter(book -> book.getBook_id().equals(id))
                .map(books -> new DescriptionBooksDto().withBookId(books.getBook_id())
                        .withBookName(books.getBook_name())
                        .withAuthorBooksId(books.getAuthor_books_id())
                )
                .findFirst();
        if (optionalDescriptionBooksDto.isEmpty()) {
            throw new BookNotFoundException("Не найдена книга с Id " + id);
        }
        return optionalDescriptionBooksDto;
    }

    public List findBookSomeAuthorName(String sometext) {
        return entityManager.createStoredProcedureQuery("get_books_by_name_autors")
                .registerStoredProcedureParameter("sometext", String.class, ParameterMode.IN)
                .setParameter("sometext", "%" + sometext + "%")
                .getResultList();
    }
}
