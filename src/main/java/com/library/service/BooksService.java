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

    public void addBooks(Books books) throws AuthorNotFoundException {
        if (authorsRepository.findByAuthorId(books.getAuthorBooksId()).isEmpty()) {
            throw new AuthorNotFoundException("No author found with ID " +
                    books.getAuthorBooksId() + " added book");
        }
        booksRepository.save(books);
    }

    public List<DescriptionBooksDto> booksList() {
        return booksRepository.findAllBy()
                .stream()
                .map(books -> new DescriptionBooksDto().withBookId(books.getBookId())
                        .withBookName(books.getBookName())
                        .withAuthorBooksId(books.getAuthorBooksId())
                )
                .collect(Collectors.toList());
    }

    public DescriptionBooksDto findBookById(Integer id) throws Throwable {
        Optional<Books> books = booksRepository.findByBookId(id);
        DescriptionBooksDto descriptionBooksDto;
        if (books.isEmpty()) {
            throw new BookNotFoundException("Book with Id not found " + id);

        } else {
            descriptionBooksDto = new DescriptionBooksDto()
                    .withBookId((books.get().getBookId()))
                    .withBookName(books.get().getBookName())
                    .withAuthorBooksId(books.get().getAuthorBooksId());
        }
        return descriptionBooksDto;
    }

    public List findBookSomeAuthorName(String sometext) {
        return entityManager.createStoredProcedureQuery("get_books_by_name_autors")
                .registerStoredProcedureParameter("sometext", String.class, ParameterMode.IN)
                .setParameter("sometext", "%" + sometext + "%")
                .getResultList();
    }
}
