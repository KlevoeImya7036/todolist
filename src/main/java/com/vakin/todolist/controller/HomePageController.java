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

import com.vakin.todolist.dto.UserDto;
import com.vakin.todolist.exception.EmailAlreadyTakenException;
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
    public String createUser(Model model, String error) {
        switch (error) {
            case "InvalidUserName":
                model.addAttribute("error", "Эта почта уже используется. Попробуйте другую.");
                break;
            case "InvalidEmail":
                model.addAttribute("error", "Это имя пользователя уже используется. Попробуйте другое.");
                break;
        }
        model.addAttribute("userdto", new UserDto());
        return "signup";
    }

    @PostMapping("signup")
    public String postUser(@Valid @ModelAttribute UserDto userdto, Model model) throws UsernameAlreadyTakenException, EmailAlreadyTakenException {
        try {
            userService.registerUser(userdto);
            return "redirect:/login";
        } catch (UsernameAlreadyTakenException e) {
            return "redirect:/signup?error=InvalidUserName";
        } catch (EmailAlreadyTakenException e) {
            return "redirect:/signup?error=InvalidEmail";
        }
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
