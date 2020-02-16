package com.notyfyd.controller;

import com.notyfyd.model.UserModel;
import com.notyfyd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody UserModel model) {
        return userService.createUser(model);
    }
}
