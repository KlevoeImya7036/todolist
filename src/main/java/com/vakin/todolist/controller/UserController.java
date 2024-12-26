package com.vakin.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.vakin.todolist.dto.UserDto;
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }
    
    @GetMapping("{id}")
    public String getUser(@PathVariable long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).orElse(null));
        return "user";
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }

    @GetMapping("edit")
    public String editUser(Model model, @RequestParam long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/user";
        }
        UserDto userdto = new UserDto();
        userdto.setName(user.getName());
        userdto.setAge(user.getAge());
        userdto.setEmail(user.getEmail());
        userdto.setPassword(user.getPassword());
        userdto.setUsername(user.getUsername());
        model.addAttribute("userdto", userdto);
        return "userEdit";
    }

    @PostMapping("edit")
    public String saveUser(Model model, @RequestParam long id, @Valid @ModelAttribute UserDto userdto, BindingResult result) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return "redirect:/user";
        }
        if (result.hasErrors()) {
            return "userEdit";
        }
        user.setName(userdto.getName());
        user.setAge(userdto.getAge());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setUsername(userdto.getUsername());
        userRepository.save(user);

        return "redirect:/user";
    }
}
