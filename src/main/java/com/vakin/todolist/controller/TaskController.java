// package com.vakin.todolist.controller;

// import java.security.Principal;
// import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.BindingResult;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// import com.vakin.todolist.dto.CommentDto;
// import com.vakin.todolist.dto.ProjectDto;
// import com.vakin.todolist.dto.TaskDto;
// import com.vakin.todolist.dto.UserDto;
// import com.vakin.todolist.exception.EmailAlreadyTakenException;
// import com.vakin.todolist.exception.InvalidAgeException;
// import com.vakin.todolist.exception.InvalidPasswordException;
// import com.vakin.todolist.exception.ProjectNotFoundException;
// import com.vakin.todolist.exception.TaskNotFoundException;
// import com.vakin.todolist.exception.UsernameAlreadyTakenException;
// import com.vakin.todolist.model.Comment;
// import com.vakin.todolist.model.User;
// import com.vakin.todolist.repository.TaskRepository;
// import com.vakin.todolist.service.CommentService;
// import com.vakin.todolist.service.ProjectService;
// import com.vakin.todolist.service.TaskService;
// import com.vakin.todolist.service.UserService;

// import jakarta.validation.Valid;

// @Controller
// @RequestMapping("project/{projectId}/task")
// public class TaskController {
    
//     @Autowired
//     private TaskService taskService;

//     @Autowired
//     private ProjectService projectService;

//     @Autowired
//     private CommentService commentService;

//     @Autowired
//     private UserService userService;

    
//     @GetMapping("{id}")
//     public String getTask(@PathVariable Long id, Model model) {
//         model.addAttribute("task", taskService.getTaskById(id));
//         model.addAttribute("comments", commentService.getAllByTask(taskService.getTaskById(id)));
//         return "task";
//     }

//     @GetMapping("{id}/edit")
//     public String showTaskEditForm(@PathVariable Long id, Model model, Principal principal) {
//         // if (!taskService.isTaskOwner(id, principal.getName())) {
//         //     return "redirect:/Task";
//         // }
//         model.addAttribute("Task", taskService.getTaskById(id));
//         return "taskEdit";
//     }

//     @PostMapping("{id}/edit")
//     public String editTask(@PathVariable Long id, @ModelAttribute TaskDto Taskdto, Model model, Principal principal) {
//         // if (!taskService.isTaskOwner(id, principal.getName())) {
//         //     return "redirect:/Task";
//         // }
//         taskService.editTask(id, Taskdto);
//         return "redirect:/task";
//     }

//     @GetMapping("{id}/delete")
//     public String showTaskDeleteForm(@PathVariable Long id, Principal principal, Model model) {
//         // if (!taskService.isTaskOwner(id, principal.getName())) {
//         //     return "redirect:/task";
//         // }
//         model.addAttribute("task", taskService.getTaskById(id));
//         return "taskDelete";
//     }

//     @PostMapping("{id}/delete")
//     public String deleteTask(@PathVariable Long id, Principal principal, Model model) {
//         // if (!taskService.isTaskOwner(id, principal.getName())) {
//         //     return "redirect:/task";
//         // }
//         taskService.deleteTask(id);
//         return "redirect:/task";
//     }

//     @GetMapping("{id}/assign")
//     public String showAssingUserForm(@PathVariable Long id, @PathVariable Long project_id, Principal principal, Model model) {
//         // if (!taskService.isTaskOwner(id, principal.getName())) {
//         //     return "redirect:/task";
//         // }
//         model.addAttribute("task", taskService.getTaskById(id));
//         model.addAttribute("users", projectService.getAllUsersInProject(project_id));
//         return "assignUserToTask";
//     }

//     @PostMapping("{id}/assign")
//     public String AssingUserToUser(@PathVariable Long id, @RequestParam("assignedUsers") Set<User> users, Principal principal, Model model) {
//         // if (!taskService.isTaskOwner(id, principal.getName())) {
//         //     return "redirect:/task";
//         // }
//         taskService.addAssignedUsers(id, users);
//         return "redirect:/task";
//     }

//     @GetMapping("/{id}/comments/create")
//     public String showCommentCreationForm(@PathVariable Long id, @PathVariable Long project_id, Model model) {
//         try {
//             model.addAttribute("comment", new Comment());
//             model.addAttribute("task", taskService.getTaskById(id));
//             return "commentCreate";
//         } catch (TaskNotFoundException | ProjectNotFoundException e) {
//             return "redirect:/project";
//         }
       
//     }


//     @PostMapping("/{id}/comments/create")
//     public String createComment(@PathVariable Long id, @PathVariable Long project_id, @ModelAttribute CommentDto comment, Model model, Principal principal) {
//         try {
//             commentService.saveComment(comment, userService.getUserByUsername(principal.getName()), taskService.getTaskById(id));
//             return "redirect:/project/" + project_id + "/task/" + id;
//         }
//         catch (TaskNotFoundException | ProjectNotFoundException e) {
//             return "redirect:/project";
//         }
//     }
// }