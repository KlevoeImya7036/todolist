package com.vakin.todolist.exception;

public class InvalidAgeException extends RuntimeException{
    public InvalidAgeException(String message) {
        super(message);
    }
}
