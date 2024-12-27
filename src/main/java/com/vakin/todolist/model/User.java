package com.vakin.todolist.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;
    
    @Column(name = "age")
    @Min(value = 18, message = "Age must be greater than 18")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;
    
    @Column(name = "username", unique = true)
    @NotBlank(message = "Username is required")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "email", unique = true)
    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
    

    public User(){}
    public User(String name, Integer age, String email, String username, String password) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "{\"id\": "+id+", \"name\": \""+name+"\", \"age\": "+age+", \"email\": \""+email+"\"}";
    }
}