package com.library.service;

import com.dto.users.DescriptionUsersDto;

import com.library.models.Role;
import com.library.models.Users;
import com.library.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    public String getPasswordByName(String name) {
        return usersRepository.findAllBy().stream()
                .filter(user -> user.getUsername().equals(name))
                .map(Users::getPassword)
                .collect(Collectors.toList())
                .get(0);
    }

    public void addUser(Integer id,
                        String name,
                        Set<Role> roles) {
        usersRepository.save(new Users(id, name, roles));
    }

    public List<DescriptionUsersDto> usersList() {
        return usersRepository.findAllBy()
                .stream()
                .map(users -> new DescriptionUsersDto()
                        .withId(users.getId())
                        .withName(users.getUsername())
                        .withRole(users.getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(Collectors.toList()))
                )
                .collect(Collectors.toList());
    }

    public Optional<DescriptionUsersDto> findUsersById(Integer id) {
        Optional<DescriptionUsersDto> optionalDescriptionUsersDto = usersRepository.findAllBy()
                .stream()
                .filter(user -> user.getId().equals(id))
                .map(users -> new DescriptionUsersDto()
                        .withId(users.getId())
                        .withName(users.getUsername())
                        .withRole(users.getRoles()
                                .stream()
                                .map(Role::getName)
                                .collect(Collectors.toList()))
                )
                .findFirst();
        return Optional.ofNullable(optionalDescriptionUsersDto.orElse(new DescriptionUsersDto().withId(id)
                .withName("User is not authenticated")));
    }
}
