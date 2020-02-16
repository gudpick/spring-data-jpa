package com.notyfyd.service;

import com.notyfyd.entity.User;
import com.notyfyd.model.UserModel;
import com.notyfyd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<Object> createUser(UserModel model) {
        User user = new User();
        if(userRepository.findByEmail(model.getEmail()).isPresent()){
            System.out.println("The email is already present");
            return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User");
        } else {
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setMobile(model.getMobile());
            user.setEmail(model.getEmail());
            userRepository.save(user);
            return ResponseEntity.ok("userCreated Successfully");
        }

    }
}
