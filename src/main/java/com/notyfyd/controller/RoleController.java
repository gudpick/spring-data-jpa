package com.notyfyd.controller;

import com.notyfyd.entity.Role;
import com.notyfyd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;
    @PostMapping("/role/create")
    public ResponseEntity<Object> createRole(@RequestBody Role role) {
        return  roleService.addRole(role);
    }
    @DeleteMapping("/role/delete/{id}")
    public ResponseEntity<Object> deleteRole(@PathVariable Long id) {
        return roleService.deleteRole(id);
    }
}
