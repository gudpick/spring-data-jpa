package com.notyfyd.controller;

import com.notyfyd.entity.User;
import com.notyfyd.model.UserModel;
import com.notyfyd.repository.RoleRepository;
import com.notyfyd.repository.UserRepository;
import com.notyfyd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/user/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
    @GetMapping("/user/details/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id).get();
    }
    @GetMapping("/user/all")
    public List<User> getAll(){
        return userRepository.findAll();
    }
}
