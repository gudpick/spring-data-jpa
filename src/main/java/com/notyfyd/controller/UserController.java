package com.notyfyd.controller;

import com.notyfyd.entity.User;
import com.notyfyd.model.UserModel;
import com.notyfyd.repository.UserRepository;
import com.notyfyd.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody UserModel user) {
        return userService.createUser(user);
    }



}
