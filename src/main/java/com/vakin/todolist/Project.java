package com.vakin.todolist;

import java.util.ArrayList;

public class Project {
    public int id;
    public String name;
    public String description;
    public ArrayList<User> admins = new ArrayList<User>();
    public ArrayList<User> users = new ArrayList<User>();
    public ArrayList<Task> tasks = new ArrayList<Task>();

    Project(int id, String name, String description, ArrayList<User> admins, ArrayList<User> users, ArrayList<Task> tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.admins = admins;
        this.users = users;
        this.tasks = tasks;
    }

    public String toString() {
        String admin = "{";
        for (User a: admins) {
            admin += a.toString() + ",";
        }
        admin += "}";
        String user = "{";
        for (User a: users) {
            user += a.toString() + ",";
        }
        user += "}";
        String task = "{";
        for (Task a: tasks) {
            task += a.toString() + ",";
        }
        task += "}";
        return "{\"id\": "+id+", \"name\": \""+name+"\", \"description\": \""+description+"\", \"admins\": "+admin+", \"users\": "+user+", \"tasks\": "+task+"}";
    }
}
