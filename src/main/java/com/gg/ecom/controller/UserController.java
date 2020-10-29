package com.gg.ecom.controller;

import com.gg.ecom.dto.UserDTO;
import com.gg.ecom.model.ERole;
import com.gg.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
//@Controller
@RestController
@RequestMapping("/api/auth/")
public class UserController {

    private UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login-form")
    public String getForm() {
        return "login";
    }

    @GetMapping("/users")
   // @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")  // or hasRole('SELLER') or hasRole('BUYER')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{userRole}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsersWithRole(@PathVariable("userRole") String role) {
        ERole eRole = ERole.valueOf("ROLE_" + role.toUpperCase());
        return ResponseEntity.ok(userService.getAllUsers(eRole));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> getUserWithId(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @DeleteMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Object> deleteUserWithId(@PathVariable("userId") Long id) {
        userService.removeUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/user/{userId})")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateUserWithId(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(new UserDTO());
    }

    @PatchMapping("/user/{})")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SELLER') or hasRole('BUYER')" )
    public ResponseEntity<UserDTO> updateCurrentlyAuthenticatedUser(@PathVariable("userId") Long id) {
        return ResponseEntity.ok(new UserDTO());
    }




}
