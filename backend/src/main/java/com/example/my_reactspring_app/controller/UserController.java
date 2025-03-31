package com.example.my_reactspring_app.controller;

import com.example.my_reactspring_app.model.User;
import com.example.my_reactspring_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.my_reactspring_app.dto.UserUpdateDTO;
import com.example.my_reactspring_app.error.ErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("User not found"));
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody User userInfo) {
        User newUser = userService.registerNewUser(userInfo);
        if (newUser == null) {
            ErrorResponse error = new ErrorResponse("User already " + 
            "exists with email: " + userInfo.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody UserUpdateDTO dto) {
        boolean success = userService.updateUser(id, dto);
        if (!success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        boolean success = userService.deleteUser(id);
        if (!success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        return ResponseEntity.noContent().build();
    }
}