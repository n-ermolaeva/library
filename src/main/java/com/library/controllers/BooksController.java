package com.library.controllers;

import com.dto.books.DescriptionBooksDto;
import com.library.exception.AuthorNotFoundException;
import com.library.models.Books;
import com.library.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BooksController {
    private final BooksService booksService;

    @GetMapping("books")
    public List<DescriptionBooksDto> getListBooks() {
        return booksService.booksList();
    }

    @GetMapping("/book/{id}")
    public DescriptionBooksDto getBookDescription(@PathVariable Integer id) throws Throwable {
        return booksService.findBookById(id);
    }

    @PostMapping("/users/book/")
    public List<DescriptionBooksDto> addBook(@RequestBody Books request) throws AuthorNotFoundException {
        booksService.addBooks(request);
        return booksService.booksList();
    }

    @GetMapping("/findbooks/{sometext}")
    public List findBooksSomeAuthorName(@PathVariable String sometext) {
        return booksService.findBookSomeAuthorName(sometext);
    }
}
