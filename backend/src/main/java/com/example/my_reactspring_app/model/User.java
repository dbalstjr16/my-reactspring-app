package com.example.my_reactspring_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name="users2")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column
    private String name;

    @Column
    private Integer age;

    public User() {}

    // ------- Id (Primary Key) -------
    public int getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    // ------- Username -------
    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // ------- Password -------
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    // ------- Password -------
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // ------- Name -------
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // ------- Age -------
    public int getAge() {
        return this.age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }


}