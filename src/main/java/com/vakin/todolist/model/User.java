package com.vakin.todolist.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private Long id;
    private String name;
    private Integer age;
    private String username;
    private String password;
    private String email;

    User(){}
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