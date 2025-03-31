package com.example.my_reactspring_app.service;

import com.example.my_reactspring_app.dto.UserUpdateDTO;
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

    public User getUserById(Integer id) {
        Optional<User> userOption = userRepository.findById(id);
        if (!userOption.isPresent()) {
            return null;
        }

        User user = userOption.get();
        return user;
    }

    public User registerNewUser(User userInfo) {
        if (userRepository.existsByEmail(userInfo.getEmail())) {
            return null;
        }

        // String hashedPassword = passwordEncoder.encode(userInfo.getPassword());
        // userInfo.setPassword(hashedPassword);
        
        User newUser = userRepository.save(userInfo);

        return newUser;
    }

    public boolean updateUser(Integer id, UserUpdateDTO dto) {
        Optional<User> userOption = userRepository.findById(id);
        if (userOption.isEmpty()) return false;

        User user = userOption.get();

        if (dto.getEmail() != null) user.setEmail(dto.getEmail());
        if (dto.getName() != null)  user.setName(dto.getName());
        if (dto.getAge() != null)   user.setAge(dto.getAge());

        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Integer id) {
        Optional<User> userOption = userRepository.findById(id);
        if (userOption.isEmpty()) return false;

        userRepository.delete(userOption.get());

        return true;
    }

}

