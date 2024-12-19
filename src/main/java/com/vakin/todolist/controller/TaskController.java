package com.vakin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.vakin.todolist.repository.TaskRepository;

@Controller
public class TaskController {
    
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("project/task")
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks";
    }
    
    @GetMapping("project/task/{id}")
    public String getTask(@PathVariable long id, Model model) {
        model.addAttribute("task", taskRepository.findById(id));
        return "task";
    }

    @DeleteMapping("project/task/{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);
    }
}
