package com.vakin.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ForumMessageDto {
    @NotBlank(message = "Необходимо название")
    private String text;
}
