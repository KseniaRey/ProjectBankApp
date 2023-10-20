package com.example.bankapp.service.impl;

import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.service.UserService;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
