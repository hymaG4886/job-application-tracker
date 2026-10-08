package com.hymavathi.jobtracker.Controller;

import com.hymavathi.jobtracker.entity.UserEntity;
import com.hymavathi.jobtracker.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity register(
            @RequestBody UserEntity user) {

        return service.register(user);
    }
}