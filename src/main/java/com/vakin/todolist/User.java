package com.vakin.todolist;

public class User {
    public String name;
    public int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "{\"name\": \""+name+"\", \"age\": "+age+"}";
    }
}