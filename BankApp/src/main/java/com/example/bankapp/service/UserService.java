package com.example.bankapp.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface UserService {
    @Transactional
    void deleteById(UUID id);
}
