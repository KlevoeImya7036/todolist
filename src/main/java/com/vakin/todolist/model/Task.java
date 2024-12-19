package com.vakin.todolist.model;

import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private ArrayList<User> assigned; // users assigned to this task
    private boolean done;

    Task(){}
    Task(String name, String description, ArrayList<User> assigned, boolean done) {
        this.name = name;
        this.description = description;
        this.assigned = assigned;
        this.done = done;
    }

    @Override
    public String toString() {
        String au = "{";
        for (User u: assigned) {
            au += u.toString() + ",";
        }
        au += "}";
        return "{\"id\": "+id+", \"name\": \""+name+"\", \"description\": \""+description+"\", \"assigned\": "+au+", \"done\": "+done+"}";
    }
}