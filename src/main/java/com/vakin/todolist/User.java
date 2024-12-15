package com.vakin.todolist;

public class User {
    public int id;
    public String name;
    public int age;
    public String email;

    User(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String toString() {
        return "{\"id\": "+id+", \"name\": \""+name+"\", \"age\": "+age+", \"email\": \""+email+"\"}";
    }
}