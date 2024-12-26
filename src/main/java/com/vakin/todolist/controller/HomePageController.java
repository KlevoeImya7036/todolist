package com.vakin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vakin.todolist.dto.UserDto;
import com.vakin.todolist.exception.EmailAlreadyTakenException;
import com.vakin.todolist.exception.InvalidAgeException;
import com.vakin.todolist.exception.InvalidPasswordException;
import com.vakin.todolist.exception.UsernameAlreadyTakenException;
import com.vakin.todolist.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class HomePageController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("signup")
    public String createUser(Model model) {
        model.addAttribute("userdto", new UserDto());
        return "signup";
    }

    @PostMapping("signup")
    public String postUser(@Valid @ModelAttribute UserDto userdto, Model model, BindingResult bindingResult, RedirectAttributes redirectAttributes) throws UsernameAlreadyTakenException, EmailAlreadyTakenException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", bindingResult);
            redirectAttributes.addFlashAttribute("user", userdto);
            return "redirect:/signup";
        }
        try {
            userService.registerUser(userdto);
            return "redirect:/login";
        } catch (UsernameAlreadyTakenException | EmailAlreadyTakenException | InvalidAgeException | InvalidPasswordException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/signup";
        }
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
