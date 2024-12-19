package com.vakin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vakin.todolist.repository.ProjectRepository;

@Controller
@RequestMapping("project")
public class ProjectController {
    
    @Autowired
    private ProjectRepository projectRepository;
    
    @GetMapping
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projectRepository.findAll());
        return "projects";
    }
    
    @GetMapping("{id}")
    public String getProject(@PathVariable long id, Model model) {
        model.addAttribute("project", projectRepository.findById(id));
        return "project";
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable long id) {
        projectRepository.deleteById(id);
    }
}
