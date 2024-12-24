package com.vakin.todolist.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TaskDto {

    @NotEmpty(message = "Name is required")
    private String name;
    private String description;
    @NotEmpty(message = "Done is required")
    private boolean done;
}
