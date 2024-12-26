package com.vakin.todolist.dto;

import com.vakin.todolist.model.Project;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TaskDto {

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    private boolean done;

    private Project project;
}
