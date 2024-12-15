package com.vakin.todolist;

import java.util.ArrayList;

public class Task {
    public int id;
    public String name;
    public String description;
    public ArrayList<User> assigned; // userы assigned to this task
    public boolean done;

    Task(int id, String name, String description, ArrayList<User> assigned, boolean done) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.assigned = assigned;
        this.done = done;
    }

    public String toString() {
        String au = "{";
        for (User u: assigned) {
            au += u.toString() + ",";
        }
        au += "}";
        return "{\"id\": "+id+", \"name\": \""+name+"\", \"description\": \""+description+"\", \"assigned\": "+au+", \"done\": "+done+"}";
    }

    // public int getId() {return id;}

    // public String getName() {return name;}

    // public String getDescription() {return description;}

    // public boolean isDone() {return done;}

    // public void setId(int id) {this.id = id;}

    // public void setName(String name) {this.name = name;}

    // public void setDescription(String description) {this.description = description;}

    // public void setDone(boolean done) {this.done = done;}
}