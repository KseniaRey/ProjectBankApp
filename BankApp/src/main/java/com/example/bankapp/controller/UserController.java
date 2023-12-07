package com.example.bankapp.controller;

import com.example.bankapp.dto.UserDto;
import com.example.bankapp.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @DeleteMapping(path = "/{id}")
    @PreAuthorize("hasAuthority('MANAGER')")
    public void deleteUserById(@PathVariable UUID id){
        userService.deleteById(id);
    }

    @GetMapping(path = "/{id}")
    public UserDto getUserById(@PathVariable UUID id){
        return userService.getUserById(id);
    }
}
