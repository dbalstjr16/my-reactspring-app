package com.example.my_reactspring_app.dto;

public class UserUpdateDTO {
    private String email;
    private String name;
    private Integer age;

    public UserUpdateDTO() {}

    // -------- Getters --------
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
 
    // -------- Setters --------
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
