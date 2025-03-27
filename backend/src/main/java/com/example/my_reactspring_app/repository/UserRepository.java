package com.example.my_reactspring_app.repository;

import com.example.my_reactspring_app.model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // You can define custom queries here, e.g.:
    // Optional<User> findByUsername(String username);
    boolean existsByEmail(String email);
}
