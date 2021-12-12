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

    public void addAuthor(Integer id,
                          String name) {
        authorsRepository.save(new Authors(id, name));
    }

    public List<DescriptionАuthorDto> authorsList() {
        return authorsRepository.findAllBy()
                .stream()
                .map(authors -> new DescriptionАuthorDto().withAuthorId(authors.getAuthor_id())
                        .withAuthorName(authors.getAuthor_name())
                )
                .collect(Collectors.toList());
    }

    public Optional<DescriptionАuthorDto> findAuthorById(Integer id) throws Throwable {
        List<Authors> authorsList = authorsRepository.findAllBy();
        Optional<DescriptionАuthorDto> optionalDescriptionAuthorDto = authorsList
                .stream()
                .filter(author -> author.getAuthor_id().equals(id))
                .map(authors -> new DescriptionАuthorDto().withAuthorId(authors.getAuthor_id())
                        .withAuthorName(authors.getAuthor_name())
                )
                .findFirst();
        if (optionalDescriptionAuthorDto.isEmpty()) {
            throw new AuthorNotFoundException("Не найден автор книги с Id " + id);
        }
        return optionalDescriptionAuthorDto;
    }
}
