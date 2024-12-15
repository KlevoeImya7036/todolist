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
public class ProjectRestController {
    public ArrayList<Project> projects = new ArrayList<Project>();

    ProjectRestController() {
        ArrayList<User> user1 = new ArrayList<User>();
        ArrayList<User> user2 = new ArrayList<User>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(1, "ничего не сломать", "не очень сложная", new ArrayList<User>(), false));
        user1.add(new User(0, "John", 18, "example@mail.com"));
        user2.add(new User(0, "John", 18, "example@mail.com"));
        user2.add(new User(1, "volodya", 40, "example@mail.com"));
        Project project1 = new Project(
            0,
            "Project 1",
            "Description 1",
            user1,
            user2,
            tasks
        );
        Project project2 = new Project(
            1,
            "Project 2",
            "Description 2",
            user2,
            user2,
            new ArrayList<Task>()
        );
        projects.add(project1);
        projects.add(project2);
    }

    @GetMapping("api/project")
    public String getAllProjects() {
        String res = "{";
        for (Project project : projects) {
            res += project.toString() + ", ";
        }
        res += "}";
        return res;
    }
    
    @GetMapping("api/project/{id}")
    public String getProject(@PathVariable int id) {
        return projects.get(id).toString();
    }

    @DeleteMapping("api/project/{id}")
    public void deleteProject(@PathVariable int id) {
        projects.remove(id);
    }

    @PostMapping("api/project")
    public void postProject(@RequestBody Project newProject) {
        projects.add(newProject);
    }

    @PutMapping("api/project/{id}")
    public void putProject(@RequestBody Project newProject, @PathVariable int id) {
        projects.set(id, newProject);
    }
}