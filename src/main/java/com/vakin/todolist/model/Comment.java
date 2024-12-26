package com.vakin.todolist.model;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;

    @CreatedDate
    private LocalDateTime writingDate;

    @NotBlank(message = "Comment text is required")
    private String text;


    @Override
    public String toString() {
        return "{\"id\": "+id+", \"taskId\": "+task.getId()+", \"userId\": "+user.getId()+", \"text\": \""+text+"\", \"writingDate\": \""+writingDate+"\"}";
    }
}
