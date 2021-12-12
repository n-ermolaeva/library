package com.library.controllers;

import com.dto.users.DescriptionUsersDto;
import com.library.models.Role;
import com.library.service.UsersService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequiredArgsConstructor
public class UsersController {
    @Autowired
    private UsersService usersService;

    @GetMapping("about/about/{id}")
    public Optional<DescriptionUsersDto> getUserDescription(@PathVariable Integer id) {
        return usersService.findUsersById(id);
    }

    @GetMapping("/users/")
    public List<DescriptionUsersDto> getListUsers() {
        return usersService.usersList();
    }

    @GetMapping("/admin/user/")
    public List<DescriptionUsersDto> addUser(@RequestParam(required = true, defaultValue = "3") Integer id,
                                             @RequestParam(required = true, defaultValue = "admin") String name,
                                             @RequestParam(required = true, defaultValue = "1, 2") Set<Role> roles,
                                             Model model) {
        usersService.addUser(id, name, roles);
        return usersService.usersList();
    }
}
