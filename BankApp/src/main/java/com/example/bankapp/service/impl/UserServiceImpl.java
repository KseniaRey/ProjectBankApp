package com.example.bankapp.service.impl;

import com.example.bankapp.dto.UserDto;
import com.example.bankapp.entity.User;
import com.example.bankapp.mapper.UserMapper;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }


    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public User findByEmailAndPassword(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) { // passwordEncoder.matches проверяет и сравнивает хеши
                return user;
            }
        }
        throw new RuntimeException("Email or password is not correct");
    }

    @Override
    public UserDto getUserById(UUID id) {
         User resultUser = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There's no User with this id"));
         return userMapper.mapToDTO(resultUser);
    }

}
