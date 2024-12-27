package com.vakin.todolist.controller;

import java.security.Principal;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vakin.todolist.dto.CommentDto;
import com.vakin.todolist.dto.TaskDto;
import com.vakin.todolist.exception.ProjectNotFoundException;
import com.vakin.todolist.exception.TaskNotFoundException;
import com.vakin.todolist.model.Comment;
import com.vakin.todolist.model.Task;
import com.vakin.todolist.service.CommentService;
import com.vakin.todolist.service.ProjectService;
import com.vakin.todolist.service.TaskService;
import com.vakin.todolist.service.UserService;

@Controller
@RequestMapping("project/{projectId}/task")
public class TaskController {
    
    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    
    @GetMapping("{id}")
    public String getTask(@PathVariable Long id, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        model.addAttribute("task", task);
        model.addAttribute("comments", commentService.getAllByTask(taskService.getTaskById(id)));
        return "task";
    }

    @PostMapping("{id}")
    public String completeTask(@PathVariable Long id, @RequestParam("done") boolean done, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        taskService.completeTask(id, done);
        return "redirect:/project/" + taskService.getTaskById(id).getProject().getId() + "/task/" + id;
    }

    @GetMapping("{id}/edit")
    public String showTaskEditForm(@PathVariable Long id, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        model.addAttribute("taskdto", taskService.getTaskById(id));
        return "taskEdit";
    }

    @PostMapping("{id}/edit")
    public String editTask(@PathVariable Long id, @ModelAttribute TaskDto taskdto, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        taskService.editTask(id, taskdto);
        return "redirect:/project/" + taskService.getTaskById(id).getProject().getId();
    }

    @GetMapping("{id}/delete")
    public String showTaskDeleteForm(@PathVariable Long id, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        model.addAttribute("task", taskService.getTaskById(id));
        return "taskDelete";
    }

    @PostMapping("{id}/delete")
    public String deleteTask(@PathVariable Long id, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        taskService.deleteTasks(id);
        return "redirect:/project/" + task.getProject().getId();
    }

    @GetMapping("{id}/assign")
    public String showAssingUserForm(@PathVariable Long id, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        model.addAttribute("task", task);
        model.addAttribute("users", projectService.getAllUsersInProject(task.getProject().getId()));
        return "assignUserToTask";
    }

    @PostMapping("{id}/assign")
    public String AssingUserToUser(@PathVariable Long id, @RequestParam("assignedUsers") Set<String> users, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        taskService.addAssignedUsers(id, users);
        return "redirect:/project/" + taskService.getTaskById(id).getProject().getId();
    }

    @GetMapping("/{id}/comments/create")
    public String showCommentCreationForm(@PathVariable Long id, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        try {
            model.addAttribute("commentdto", new Comment());
            model.addAttribute("task", taskService.getTaskById(id));
            return "commentCreate";
        } catch (TaskNotFoundException | ProjectNotFoundException e) {
            return "redirect:/project";
        }
    }

    @PostMapping("/{id}/comments/create")
    public String createComment(@PathVariable Long id, @ModelAttribute CommentDto comment, Model model, Principal principal) {
        Task task = taskService.getTaskById(id);
        if (!projectService.isUserInProject(task.getProject().getId(), principal.getName())) {
            return "redirect:/project";
        }
        try {
            commentService.saveComment(comment, userService.getUserByUsername(principal.getName()), taskService.getTaskById(id));
            return "redirect:/project/" + taskService.getTaskById(id).getProject().getId() + "/task/" + id;
        }
        catch (TaskNotFoundException | ProjectNotFoundException e) {
            return "redirect:/project";
        }
    }
}