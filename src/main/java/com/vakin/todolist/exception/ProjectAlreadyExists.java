package com.vakin.todolist.exception;

public class ProjectAlreadyExists extends RuntimeException{
    public ProjectAlreadyExists(String message) {
        super(message);
    }
}
