package com.vakin.todolist.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {
    
    @NotEmpty(message = "Name is required")
    private String name;

    private Integer age;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

    @Email(message = "Email is not valid")
    private String email;
}
