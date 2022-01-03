package com.library.repositories;

import com.library.models.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorsRepository extends JpaRepository<Authors, Integer> {
    List<Authors> findAllBy();

    Optional<Authors> findByAuthorId(Integer id);

}
