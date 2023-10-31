package com.example.bankapp.service;

import com.example.bankapp.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserService {
    @Transactional
    void deleteById(UUID id);

    User findByEmailAndPassword(String email, String password);
}
