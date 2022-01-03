package com.library.repositories;

import com.library.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Books, Integer> {
    List<Books> findAllBy();

    Optional<Books> findByBookId(Integer id);
}

