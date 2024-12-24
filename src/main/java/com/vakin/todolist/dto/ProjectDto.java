package com.vakin.todolist.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ProjectDto {
    @NotBlank(message = "Name is required")
    private String name;

    private String description;
}
