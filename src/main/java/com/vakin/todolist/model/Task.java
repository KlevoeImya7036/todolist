package com.vakin.todolist.model;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> assigned = new HashSet<User>();

    @Column(name = "done", nullable = false)
    private boolean done;

    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;


    public void addAssignedUser(Set<User> users) {
        assigned.addAll(users);
    }

    public void removeAssignedUser(User user) {
        assigned.remove(user);
    }

    // @Override
    // public String toString() {
    //     String au = "{";
    //     for (User u: assigned) {
    //         au += u.toString() + ",";
    //     }
    //     au += "}";
    //     return "{\"id\": "+id+", \"name\": \""+name+"\", \"description\": \""+description+"\", \"assigned\": "+au+", \"done\": "+done+"}";
    // }
}