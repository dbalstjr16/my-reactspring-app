package com.example.my_reactspring_app.service;

import com.example.my_reactspring_app.model.User;
import com.example.my_reactspring_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUserId(Integer id) {
        Optional<User> userOption = userRepository.findById(id);

        if (!userOption.isPresent()) {
            return null;
        }

        User user = userOption.get();
        return user;
    }

    public HttpStatus registerNewUser(User userInfo) {

        System.out.println("inside service: " + userInfo);

        if (userRepository.existsByEmail(userInfo.getEmail())) {
            return HttpStatus.CONFLICT;
        }

        // String hashedPassword = passwordEncoder.encode(userInfo.getPassword());
        // userInfo.setPassword(hashedPassword);
        
        userRepository.save(userInfo);

        return HttpStatus.CREATED;
    }

}

