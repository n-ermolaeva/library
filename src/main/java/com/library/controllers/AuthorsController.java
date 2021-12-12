package com.library.controllers;

import com.dto.authors.DescriptionАuthorDto;
import com.library.service.AuthorsService;
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
public class AuthorsController {
    private final AuthorsService authorsService;

    @GetMapping("authors")
    public List<DescriptionАuthorDto> getListAuthor() {
        return authorsService.authorsList();
    }

    @GetMapping("/author/{id}")
    public Optional<DescriptionАuthorDto> getAuthorDescription(@PathVariable Integer id) throws Throwable {
        return authorsService.findAuthorById(id);
    }

    @GetMapping("/users/author/")
    public List<DescriptionАuthorDto> addAuthor(@RequestParam(required = true, defaultValue = "1") Integer authorId,
                                                @RequestParam(required = true, defaultValue = "Tolstoy") String authorName,
                                                Model model) {
        authorsService.addAuthor(authorId, authorName);
        return authorsService.authorsList();
    }

}
