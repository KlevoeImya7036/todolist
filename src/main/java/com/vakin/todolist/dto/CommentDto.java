package com.vakin.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDto {
    
    @NotBlank(message = "Comment text is required")
    private String text;
}
