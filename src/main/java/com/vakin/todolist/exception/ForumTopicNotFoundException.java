package com.vakin.todolist.exception;

public class ForumTopicNotFoundException extends RuntimeException{
    public ForumTopicNotFoundException(String message) {
        super(message);
    }
}
