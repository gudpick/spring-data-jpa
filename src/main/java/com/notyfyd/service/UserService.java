package com.notyfyd.service;

import com.notyfyd.entity.Role;
import com.notyfyd.entity.User;
import com.notyfyd.model.UserModel;
import com.notyfyd.repository.RoleRepository;
import com.notyfyd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

//    public ResponseEntity<Object> createUser(UserModel model) {
//        User user = new User();
//        if(userRepository.findByEmail(model.getEmail()).isPresent()){
//            System.out.println("The email is already present");
//            return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User");
//        } else {
//            user.setFirstName(model.getFirstName());
//            user.setLastName(model.getLastName());
//            user.setMobile(model.getMobile());
//            user.setEmail(model.getEmail());
//
//            User savedUser = userRepository.save(user);
//            if(userRepository.findById(savedUser.getId()).isPresent())
//            return ResponseEntity.ok("User Created Successfully");
//            else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
//        }
//    }
    public ResponseEntity<Object> createUser(User user) {
        User newUser = new User();
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            System.out.println("The email is already present");
            return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User");
        } else {
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setMobile(user.getMobile());
            newUser.setEmail(user.getEmail());
            newUser.setRole(user.getRole());

            User savedUser = userRepository.save(user);
            if(userRepository.findById(savedUser.getId()).isPresent())
                return ResponseEntity.ok("User Created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
        }
    }
    public ResponseEntity<Object> deleteRole(Long id) {
        if(roleRepository.findById(id).isPresent()){
            roleRepository.deleteById(id);
            if(roleRepository.findById(id).isPresent()){
                return ResponseEntity.unprocessableEntity().body("Failed to delete the specified record");
            } else return ResponseEntity.ok().body("Successfully deleted specified record");
        } else
            return ResponseEntity.unprocessableEntity().body("No Records Found");
    }
//    public UserModel getUser(Long id) {
//        User user = userRepository.findById(id).get();
//        UserModel model = new UserModel();
//        model.setFirstName(user.getFirstName());
//        model.setLastName(user.getLastName());
//        model.setMobile(user.getMobile());
//        model.setEmail(user.getEmail());
//        Role newRole = new Role();
//        newRole.setName(user.getRole().getName());
//        newRole.setDescription(user.getRole().getDescription());
//        model.setRole(newRole);
//        return model;
//    }
}
