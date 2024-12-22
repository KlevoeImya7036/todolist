package com.vakin.todolist.model;

import java.util.HashSet;
import java.util.Set;

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
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "admin_id")
    private User admin;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<User>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "project_task",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private Set<Task> tasks = new HashSet<Task>();

    public Project(){}
    Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // @Override
    // public String toString() {
    //     String admin = "{";
    //     for (User a: admins) {
    //         admin += a.toString() + ",";
    //     }
    //     admin += "}";
    //     String user = "{";
    //     for (User a: users) {
    //         user += a.toString() + ",";
    //     }
    //     user += "}";
    //     String task = "{";
    //     for (Task a: tasks) {
    //         task += a.toString() + ",";
    //     }
    //     task += "}";
    //     return "{\"id\": "+id+", \"name\": \""+name+"\", \"description\": \""+description+"\", \"admins\": "+admin+", \"users\": "+user+", \"tasks\": "+task+"}";
    // }
}
