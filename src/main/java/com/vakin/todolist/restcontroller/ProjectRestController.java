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

import com.vakin.todolist.model.Project;
import com.vakin.todolist.repository.ProjectRepository;

@RestController
@RequestMapping("api/project")
public class ProjectRestController {
    
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public String getAllProjects() {
        String res = "{";
        for (Project project : projectRepository.findAll()) {
            res += project.toString() + ", ";
        }
        res += "}";
        return res;
    }
    
    @GetMapping("{id}")
    public String getProject(@PathVariable long id) {
        return projectRepository.findById(id).toString();
    }

    @DeleteMapping("{id}")
    public void deleteProject(@PathVariable long id) {
        projectRepository.deleteById(id);
    }

    @PostMapping
    public void postProject(@RequestBody Project newProject) {
        projectRepository.save(newProject);
    }

    // @PutMapping("{id}")
    // public void putProject(@RequestBody Project newProject, @PathVariable long id) {
    //     projectRepository.set(id, newProject);
    // }
}