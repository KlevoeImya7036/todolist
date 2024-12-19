package com.vakin.todolist.dto;

import jakarta.validation.constraints.*;

public class UserDto {
    
    @NotEmpty(message = "Name is required")
    private String name;

    @Min(value = 18, message = "Age must be greater than 18")
    private Integer age;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Email(message = "Email is not valid")
    private String email;

    public UserDto(){}
    public UserDto(String name, Integer age, String email, String password, String username) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
