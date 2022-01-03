package com.library.service;

import com.dto.authors.DescriptionАuthorDto;
import com.library.exception.AuthorNotFoundException;
import com.library.models.Authors;
import com.library.repositories.AuthorsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional

public class AuthorsService {
    private final AuthorsRepository authorsRepository;

    public void addAuthor(Authors authors) {
        authorsRepository.save(authors);
    }

    public List<DescriptionАuthorDto> authorsList() {
        return authorsRepository.findAllBy()
                .stream()
                .map(authors -> new DescriptionАuthorDto().withAuthorId(authors.getAuthorId())
                        .withAuthorName(authors.getAuthorName())
                )
                .collect(Collectors.toList());
    }

    public DescriptionАuthorDto findAuthorById(Integer id) throws Throwable {
        Optional<Authors> optionalAuthors = authorsRepository.findByAuthorId(id);
        DescriptionАuthorDto descriptionАuthorDto;
        if (optionalAuthors.isEmpty()) {
            throw new AuthorNotFoundException("book author not found with ID " + id);
        } else {
            descriptionАuthorDto = new DescriptionАuthorDto()
                    .withAuthorId((optionalAuthors.get().getAuthorId()))
                    .withAuthorName(optionalAuthors.get().getAuthorName());
        }
        return descriptionАuthorDto;
    }
}
