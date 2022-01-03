package com.library.controllers;

import com.dto.users.DescriptionUsersDto;
import com.library.exception.UserNotFoundException;
import com.library.models.Role;
import com.library.models.Users;
import com.library.service.UsersService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Set;


@Controller
@RequiredArgsConstructor
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/about")
    public String greetingForm(Model model) {
        model.addAttribute("users", new Users());
        return "about";
    }

    @PostMapping("/about")
    @ResponseBody
    public DescriptionUsersDto greetingSubmit(@ModelAttribute Users users,
                                              Model model) throws UserNotFoundException {
        model.addAttribute("users", users);
        return usersService.findUsersById(users.getUsername(), users.getPassword());
    }

    @GetMapping("/admin/users/")
    @ResponseBody
    public List<DescriptionUsersDto> getListUsers() {
        return usersService.usersList();
    }

    @PostMapping(value = "/admin/user/{id}/{name}/{roles}")
    @ResponseBody
    public List<DescriptionUsersDto> addUser(@PathVariable Integer id,
                                             @PathVariable String name,
                                             @PathVariable Set<Role> roles) {
        usersService.addUser(id, name, roles);
        return usersService.usersList();
    }
}
