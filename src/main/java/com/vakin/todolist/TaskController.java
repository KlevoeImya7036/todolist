package com.vakin.todolist;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskController {
    
    public ArrayList<Task> tasks = new ArrayList<Task>();

    TaskController() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(0, "John", 19, "example@mail.com"));
        tasks.add(new Task(0, "ничего не сломать", "очень сложная", users, true));
        tasks.add(new Task(1, "всё сломать", "не очень сложная", new ArrayList<User>(), false));
    }

    @GetMapping("project/task")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", tasks);
        return "tasks";
    }
    
    @GetMapping("project/task/{id}")
    public String getTask(@PathVariable int id, Model model) {
        model.addAttribute("task", tasks.get(id));
        return "task";
    }

    @DeleteMapping("project/task/{id}")
    public void deleteTask(@PathVariable int id) {
        tasks.remove(id);
    }
}
