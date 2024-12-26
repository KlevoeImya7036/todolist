package com.vakin.todolist.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vakin.todolist.dto.ProjectDto;
import com.vakin.todolist.dto.TaskDto;
import com.vakin.todolist.exception.EmailAlreadyTakenException;
import com.vakin.todolist.exception.UsernameAlreadyTakenException;
import com.vakin.todolist.service.ProjectService;
// import com.vakin.todolist.service.TaskService;
import com.vakin.todolist.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    // @Autowired
    // private TaskService taskService;
    
    @GetMapping
    public String getAllProjects(Model model, Principal principal) {
        model.addAttribute("projects", projectService.getAllProjectsByUser(userService.findByUsername(principal.getName())));
        return "projects";
    }
    
    @GetMapping("{id}")
    public String getProjectById(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("project", projectService.getProjectById(id));
        return "project";
    }

    @GetMapping("create")
    public String showProjectCreationForm(Model model) {
        model.addAttribute("projectdto", new ProjectDto());
        return "projectCreate";
    }

    @PostMapping("create")
    public String createProject(@ModelAttribute ProjectDto projectdto, Principal principal) {
        projectService.saveProject(projectdto, principal.getName());
        return "redirect:/project";
    }

    @GetMapping("{id}/edit")
    public String showProjectEditForm(@PathVariable Long id, Model model, Principal principal) {
        if (!projectService.isProjectOwner(id, principal.getName())) {
            return "redirect:/project";
        }
        model.addAttribute("project", projectService.getProjectById(id));
        return "projectEdit";
    }

    @PostMapping("{id}/edit")
    public String editProject(@PathVariable Long id, @ModelAttribute ProjectDto projectdto, Model model, Principal principal) {
        if (!projectService.isProjectOwner(id, principal.getName())) {
            return "redirect:/project";
        }
        projectService.editProject(id, projectdto);
        return "redirect:/project";
    }

    @GetMapping("{id}/delete")
    public String showProjectDeleteForm(@PathVariable Long id, Principal principal, Model model) {
        if (!projectService.isProjectOwner(id, principal.getName())) {
            return "redirect:/project";
        }
        model.addAttribute("project", projectService.getProjectById(id));
        return "projectDelete";
    }

    @PostMapping("{id}/delete")
    public String deleteProject(@PathVariable Long id, Principal principal, Model model) {
        if (!projectService.isProjectOwner(id, principal.getName())) {
            return "redirect:/project";
        }
        projectService.deleteProject(id);
        return "redirect:/project";
    }

    @GetMapping("{id}/adduser")
    public String getAddUserForm(@PathVariable Long id, Model model, Principal principal) {
        if (!projectService.isProjectOwner(id, principal.getName())) {
            return "redirect:/project";
        }
        model.addAttribute("allUsernames", userService.getAllUsernames());
        return "userAdd";
    }
    
    @PostMapping("{id}/adduser")
    public String addUser(@PathVariable Long id, @RequestParam String username, Principal principal, Model model) {
        if (!projectService.isProjectOwner(id, principal.getName())) {
            return "redirect:/project";
        }
        projectService.addUserToProject(id, username);
        return "redirect:/project/" + id;
    }

    // @GetMapping("{id}/taskcreate")
    // public String createUser(Model model) {
    //     model.addAttribute("taskdto", new TaskDto());
    //     return "taskCreate";
    // }

    // @PostMapping("{id}/taskcreate")
    // public String saveUser(@Valid @ModelAttribute TaskDto taskdto, @PathVariable Long id, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
    //     if (bindingResult.hasErrors()) {
    //         redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.task", bindingResult);
    //         redirectAttributes.addFlashAttribute("task", taskdto);
    //         return "redirect:/project/" + id + "/taskCreate";
    //     }
    //     try {
    //         taskService.saveTask(taskdto, id);
    //         return "redirect:/project/" + id;
    //     } catch (Exception e) {
    //         redirectAttributes.addFlashAttribute("error", e.getMessage());
    //         return "redirect:/project/" + id + "/taskCreate";
    //     }
    // }
}