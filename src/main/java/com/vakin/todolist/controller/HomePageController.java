package com.vakin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vakin.todolist.dto.UserDto;
import com.vakin.todolist.exception.EmailAlreadyTakenException;
import com.vakin.todolist.exception.UsernameAlreadyTakenException;
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.UserRepository;
import com.vakin.todolist.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class HomePageController {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/singup")
    public String createUser(Model model) {
        model.addAttribute("userdto", new UserDto());
        return "singup";
    }

    @PostMapping("/singup")
    public String postUser(@Valid @ModelAttribute UserDto userdto, Model model, BindingResult result) {
        try {
            userService.registerUser(userdto);
            return "redirect:/login";
        } catch (UsernameAlreadyTakenException | EmailAlreadyTakenException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/singup" + e.getMessage();
        }
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
