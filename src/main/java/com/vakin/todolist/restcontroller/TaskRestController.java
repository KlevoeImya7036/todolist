package com.vakin.todolist.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vakin.todolist.model.Task;
import com.vakin.todolist.repository.TaskRepository;

@RestController
public class TaskRestController {
    
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("api/project/task")
    public String getAllTasks() {
        String res = "{";
        for (Task task : taskRepository.findAll()) {
            res += task.toString() + ", ";
        }
        res += "}";
        return res;
    }
    
    @GetMapping("api/project/task/{id}")
    public String getTask(@PathVariable long id) {
        return taskRepository.findById(id).toString();
    }

    @DeleteMapping("api/project/task/{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);
    }

    // @PostMapping("api/project/task")
    // public void postTask(@RequestBody Task newTask) {
    //     taskRepository.add(newTask);
    // }

    // @PutMapping("api/project/task/{id}")
    // public void putTask(@RequestBody Task newTask, @PathVariable int id) {
    //     taskRepository.set(id, newTask);
    // }
}