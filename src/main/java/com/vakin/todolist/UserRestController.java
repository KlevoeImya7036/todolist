package com.vakin.todolist;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class UserRestController {
    public ArrayList<User> people = new ArrayList<User>();

    UserRestController() {
        people.add(new User(0, "John", 18, "example@mail.com"));
        people.add(new User(1, "Jane", 19, "example@mail.com"));
    }

    @GetMapping("api/user")
    public String getAllUsers() {
        String res = "{";
        for (User user : people) {
            res += user.toString() + ", ";
        }
        res += "}";
        return res;
    }
    
    @GetMapping("api/user/{id}")
    public String getUser(@PathVariable int id) {
        return people.get(id).toString();
    }

    @PostMapping("api/user")
    public void postUser(@RequestBody User newUser) {
        people.add(newUser);
    }

    @PutMapping("api/user/{id}")
    public void putUser(@RequestBody User newUser, @PathVariable int id) {
        people.set(id, newUser);
    }

    @DeleteMapping("api/user/{id}")
    public void deleteUser(@PathVariable int id) {
        people.remove(id);
    }
}
