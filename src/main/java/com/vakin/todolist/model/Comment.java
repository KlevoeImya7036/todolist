package com.vakin.todolist.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "comments")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Comment text is required")
    private String text;

    @CreatedDate
    private LocalDateTime writingDate;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;


    @Override
    public String toString() {
        return "{\"id\": "+id+", \"taskId\": "+task.getId()+", \"userId\": "+author.getId()+", \"text\": \""+text+"\", \"writingDate\": \""+writingDate+"\"}";
    }
}