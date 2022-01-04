package com.library.service;

import com.dto.users.DescriptionUsersDto;

import com.library.exception.UserNotFoundException;
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
        return usersRepository.findByUsername(name)
                .getPassword();
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

    public DescriptionUsersDto findUsersById(String name,
                                             String password) throws UserNotFoundException {
        Optional<Users> optionalDescriptionUsersDto =
                usersRepository.findByUsernameAndPassword(name, password);
        DescriptionUsersDto descriptionUsersDto;
        if (optionalDescriptionUsersDto.isEmpty()) {
            throw new UserNotFoundException("User is not found with name " + name + " password " + password);

        } else {
            descriptionUsersDto = new DescriptionUsersDto()
                    .withId(optionalDescriptionUsersDto.get().getId())
                    .withName(optionalDescriptionUsersDto.get().getUsername())
                    .withRole(optionalDescriptionUsersDto.get().getRoles()
                            .stream()
                            .map(Role::getName)
                            .collect(Collectors.toList()));
        }

        return descriptionUsersDto;
    }
}
