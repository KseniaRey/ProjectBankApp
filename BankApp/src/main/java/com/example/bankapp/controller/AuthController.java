package com.example.bankapp.controller;

import com.example.bankapp.dto.UserDto;
import com.example.bankapp.entity.User;
import com.example.bankapp.security.Jwtprovider;
import com.example.bankapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/auth")
public class AuthController {
    private final UserService userService;
    private final Jwtprovider jwtProvider;

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody UserDto userDto) {
        User user = userService.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.ok().body(jwtProvider.generateToken(user.getEmail()));
    }
}