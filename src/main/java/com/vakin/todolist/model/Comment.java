package com.vakin.todolist.model;

import java.util.Date;

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
    
    @Column(name = "task_id", nullable = false, updatable = false)
    private Long taskId;
    
    @Column(name = "user_id", nullable = false, updatable = false)
    private Long userId;

    @NotBlank(message = "Comment text is required")
    private String text;

    private Date writingDate;

    Comment(){}
    Comment(Long taskId, Long userId, String text, Date writingDate) {
        this.taskId = taskId;
        this.userId = userId;
        this.text = text;
        this.writingDate = writingDate;
    }

    @Override
    public String toString() {
        return "{\"id\": "+id+", \"taskId\": "+taskId+", \"userId\": "+userId+", \"text\": \""+text+"\", \"writingDate\": \""+writingDate+"\"}";
    }
}
