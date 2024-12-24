package com.vakin.todolist.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
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
}
