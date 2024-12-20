package com.vakin.todolist.model;

import java.util.ArrayList;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "description")
    private String description;
    
    private ArrayList<User> admins = new ArrayList<User>();
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Task> tasks = new ArrayList<Task>();

    Project(){}
    Project(String name, String description, ArrayList<User> admins, ArrayList<User> users, ArrayList<Task> tasks) {
        this.name = name;
        this.description = description;
        this.admins = admins;
        this.users = users;
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        String admin = "{";
        for (User a: admins) {
            admin += a.toString() + ",";
        }
        admin += "}";
        String user = "{";
        for (User a: users) {
            user += a.toString() + ",";
        }
        user += "}";
        String task = "{";
        for (Task a: tasks) {
            task += a.toString() + ",";
        }
        task += "}";
        return "{\"id\": "+id+", \"name\": \""+name+"\", \"description\": \""+description+"\", \"admins\": "+admin+", \"users\": "+user+", \"tasks\": "+task+"}";
    }
}
