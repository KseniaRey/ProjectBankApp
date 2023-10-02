package com.example.bankapp.controller;

import com.example.bankapp.entity.User;
import com.example.bankapp.service.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @DeleteMapping(path = "/delete")
    public void deleteUserById(@RequestParam UUID id){
        userService.deleteById(id);
    }
}
