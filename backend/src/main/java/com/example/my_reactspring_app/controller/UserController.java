package com.example.my_reactspring_app.controller;

import com.example.my_reactspring_app.model.User;
import com.example.my_reactspring_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public User getUserByUserId(@PathVariable("id") Integer id) {
        return userService.getUserByUserId(id);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody User userInfo) {
        
        System.out.println("inside controller: " + userInfo);
        HttpStatus statusCode = userService.registerNewUser(userInfo);
        if (statusCode != HttpStatus.CREATED) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ("User already exists with email: " + 
                userInfo.getEmail()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}