package com.notyfyd.service;

import com.notyfyd.entity.User;
import com.notyfyd.repository.RoleRepository;
import com.notyfyd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<Object> createUser(User model) {
        User user = new User();
        if (userRepository.findByEmail(model.getEmail()).isPresent()) {
            System.out.println("The email is already present");
            return ResponseEntity.badRequest().body("The Email is already Present, Failed to Create new User");
        } else {
            user.setFirstName(model.getFirstName());
            user.setLastName(model.getLastName());
            user.setMobile(model.getMobile());
            user.setEmail(model.getEmail());
            user.setRole(model.getRole());

            User savedUser = userRepository.save(user);
            if (userRepository.findById(savedUser.getId()).isPresent())
                return ResponseEntity.ok("User Created Successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed Creating User as Specified");
        }
    }


    @Transactional
    public ResponseEntity<Object> updateUser(User user, Long id) {
        if(userRepository.findById(id).isPresent()) {
            User newUser = userRepository.findById(id).get();
            for(int i=0; i< user.getRole().size(); i++){
                if(roleRepository.findById(user.getRole().get(i).getId()).isPresent()){
                    roleRepository.deleteById(user.getRole().get(i).getId());
                    if(roleRepository.findById(user.getRole().get(i).getId()).isPresent())
                        return ResponseEntity.unprocessableEntity().body("Failed to update user");
                }
            }
            newUser.setFirstName(user.getFirstName());
            newUser.setLastName(user.getLastName());
            newUser.setMobile(user.getMobile());
            newUser.setEmail(user.getEmail());
            newUser.setRole(user.getRole());
            User savedUser = userRepository.save(newUser);
            if(userRepository.findById(savedUser.getId()).isPresent())
                return  ResponseEntity.accepted().body("User updated successfully");
            else return ResponseEntity.unprocessableEntity().body("Failed updating the user specified");
        } else return ResponseEntity.unprocessableEntity().body("Cannot find the user specified");
    }

    public ResponseEntity<Object> deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            if (userRepository.findById(id).isPresent())
                return ResponseEntity.unprocessableEntity().body("Failed to Delete the specified User");
            else return ResponseEntity.ok().body("Successfully deleted the specified user");
        } else return ResponseEntity.badRequest().body("Cannot find the user specified");
    }
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
