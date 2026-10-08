package com.hymavathi.jobtracker.service;

import com.hymavathi.jobtracker.entity.UserEntity;
import com.hymavathi.jobtracker.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity register(UserEntity user) {

        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return repository.save(user);
    }
}