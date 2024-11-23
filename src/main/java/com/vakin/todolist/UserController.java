package com.vakin.todolist;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    public ArrayList<User> people = new ArrayList<User>();

    UserController() {
        people.add(new User("John", 18));
        people.add(new User("Jane", 19));
    }

    @GetMapping("user")
    public String getAllUsers(Model model) {
        model.addAttribute("users", people);
        return "users";
    }
    
    @GetMapping("user/{id}")
    public String getUser(@PathVariable int id, Model model) {
        model.addAttribute("user", people.get(id));
        return "user";
    }

    @DeleteMapping("user/{id}")
    public void deleteUser(@PathVariable int id) {
        people.remove(id);
    }
}
