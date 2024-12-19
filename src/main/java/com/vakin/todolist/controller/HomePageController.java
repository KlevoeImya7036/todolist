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
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class HomePageController {
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("singup")
    public String createUser(Model model) {
        model.addAttribute("userdto", new UserDto());
        return "singup";
    }

    @PostMapping("singup")
    public String postUser(@Valid @ModelAttribute UserDto userdto, BindingResult result) {
        if (result.hasErrors()) {
            return "singup";
        }
        User user = new User(userdto.getName(), userdto.getAge(), userdto.getEmail(), userdto.getUsername(), userdto.getPassword());
        userRepository.save(user);

        return "redirect:/login";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
