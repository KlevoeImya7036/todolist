package com.vakin.todolist;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskRestController {
 
    public ArrayList<Task> tasks = new ArrayList<Task>();

    TaskRestController() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(0, "John", 19, "example@mail.com"));
        users.add(new User(1, "Jane", 20, "example@mail.com"));
        tasks.add(new Task(0, "ничего не сломать", "очень сложная", users, true));
        tasks.add(new Task(1, "всё сломать", "не очень сложная", new ArrayList<User>(), false));
    }

    @GetMapping("api/project/task")
    public String getAllTasks() {
        String res = "{";
        for (Task task : tasks) {
            res += task.toString() + ", ";
        }
        res += "}";
        return res;
    }
    
    @GetMapping("api/project/task/{id}")
    public String getTask(@PathVariable int id) {
        return tasks.get(id).toString();
    }

    @PostMapping("api/project/task")
    public void postTask(@RequestBody Task newTask) {
        tasks.add(newTask);
    }

    @PutMapping("api/project/task/{id}")
    public void putTask(@RequestBody Task newTask, @PathVariable int id) {
        tasks.set(id, newTask);
    }

    @DeleteMapping("api/project/task/{id}")
    public void deleteTask(@PathVariable int id) {
        tasks.remove(id);
    }
}