package com.library.controllers;

import com.dto.books.DescriptionBooksDto;
import com.library.exception.AuthorNotFoundException;
import com.library.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BooksController {
    private final BooksService booksService;

    @GetMapping("books")
    public List<DescriptionBooksDto> getListBooks() {
        return booksService.booksList();
    }

    @GetMapping("/book/{id}")
    public Optional<DescriptionBooksDto> getBookDescription(@PathVariable Integer id) throws Throwable {
        return booksService.findBookById(id);
    }

    @GetMapping("/users/book/")
    public List<DescriptionBooksDto> addBook(@RequestParam(required = true, defaultValue = "7") Integer bookId,
                                             @RequestParam(required = true, defaultValue = "Heart of a dog") String bookName,
                                             @RequestParam(required = true, defaultValue = "3") Integer authorBooksId,
                                             Model model) throws AuthorNotFoundException {
        booksService.addBooks(bookId, bookName, authorBooksId);
        return booksService.booksList();
    }

    @GetMapping("/findbooks/{sometext}")
    public List findBooksSomeAuthorName(@PathVariable String sometext) {
        return booksService.findBookSomeAuthorName(sometext);
    }
}
