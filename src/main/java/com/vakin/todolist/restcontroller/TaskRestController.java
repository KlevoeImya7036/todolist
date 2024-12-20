package com.vakin.todolist.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vakin.todolist.model.Task;
import com.vakin.todolist.repository.TaskRepository;

@RestController
@RequestMapping("api/project")
public class TaskRestController {
    
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("task")
    public String getAllTasks() {
        String res = "{";
        for (Task task : taskRepository.findAll()) {
            res += task.toString() + ", ";
        }
        res += "}";
        return res;
    }
    
    @GetMapping("task/{id}")
    public String getTask(@PathVariable long id) {
        return taskRepository.findById(id).toString();
    }

    @DeleteMapping("task/{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);
    }

    // @PostMapping("task")
    // public void postTask(@RequestBody Task newTask) {
    //     taskRepository.add(newTask);
    // }

    // @PutMapping("task/{id}")
    // public void putTask(@RequestBody Task newTask, @PathVariable int id) {
    //     taskRepository.set(id, newTask);
    // }
}