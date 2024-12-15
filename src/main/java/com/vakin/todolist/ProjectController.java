package com.vakin.todolist;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProjectController {
    public ArrayList<Project> projects = new ArrayList<Project>();

    ProjectController() {
        ArrayList<User> user1 = new ArrayList<User>();
        ArrayList<User> user2 = new ArrayList<User>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Task(1, "ничего не сломать", "не очень сложная", new ArrayList<User>(), false));
        user1.add(new User(0, "John", 18, "example@mail.com"));
        user2.add(new User(0, "John", 18, "example@mail.com"));
        user2.add(new User(1, "Volodya", 40, "example@mail.com"));
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

    @GetMapping("project")
    public String getAllProjects(Model model) {
        model.addAttribute("projects", projects);
        return "projects";
    }
    
    @GetMapping("project/{id}")
    public String getProject(@PathVariable int id, Model model) {
        model.addAttribute("project", projects.get(id));
        return "project";
    }

    @DeleteMapping("project/{id}")
    public void deleteProject(@PathVariable int id) {
        projects.remove(id);
    }
}
