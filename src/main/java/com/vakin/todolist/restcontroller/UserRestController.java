package com.vakin.todolist.restcontroller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("api/user")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String getAllUsers() {
        List<User> ulist = userRepository.findAll();
        String res = "{";
        for (User user : ulist) {
            res += user.toString() + ", ";
        }
        res += "}";
        return res;
    }
    
    @GetMapping("{id}")
    public String getUser(@PathVariable long id) {
        return userRepository.findById(id).toString();
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable long id) {
        userRepository.deleteById(id);
    }

    // @PostMapping
    // public void postUser(@RequestBody User newUser) {
    //     List<User> ulist = userRepository.findAll();
    //     ulist.add(newUser);
    // }

    // @PutMapping("{id}")
    // public void putUser(@RequestBody User newUser, @PathVariable int id) {
    //     List<User> ulist = userRepository.findAll();
    //     ulist.set(id, newUser);
    // }
}
