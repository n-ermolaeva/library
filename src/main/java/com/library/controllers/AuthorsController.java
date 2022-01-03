package com.library.controllers;

import com.dto.authors.DescriptionАuthorDto;
import com.library.models.Authors;
import com.library.service.AuthorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorsController {
    private final AuthorsService authorsService;

    @GetMapping("authors")
    public List<DescriptionАuthorDto> getListAuthor() {
        return authorsService.authorsList();
    }

    @GetMapping("/author/{id}")
    public DescriptionАuthorDto getAuthorDescription(@PathVariable Integer id) throws Throwable {
        return authorsService.findAuthorById(id);
    }

    @PostMapping("/users/author/")
    public List<DescriptionАuthorDto> addAuthor(@RequestBody Authors request) {
        authorsService.addAuthor(request);
        return authorsService.authorsList();
    }

}
