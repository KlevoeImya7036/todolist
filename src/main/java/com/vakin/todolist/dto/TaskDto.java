package com.vakin.todolist.dto;

import jakarta.validation.constraints.NotEmpty;

public class TaskDto {

    @NotEmpty(message = "Name is required")
    private String name;
    private String description;
    @NotEmpty(message = "Done is required")
    private boolean done;


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isDone() {
        return done;
    }
    public void setDone(boolean done) {
        this.done = done;
    }
}
