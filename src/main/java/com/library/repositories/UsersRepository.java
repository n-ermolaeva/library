package com.library.repositories;

import com.library.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    List<Users> findAllBy();

    Optional<Users> findByUsernameAndPassword(String username,
                                              String password);

    Users findByUsername(String username);

}
