package com.vakin.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vakin.todolist.dto.UserDto;
import com.vakin.todolist.exception.EmailAlreadyTakenException;
import com.vakin.todolist.exception.UserNotFoundException;
import com.vakin.todolist.exception.UsernameAlreadyTakenException;
import com.vakin.todolist.model.Project;
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User registerUser(UserDto user) throws UsernameAlreadyTakenException, EmailAlreadyTakenException{
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyTakenException("InvalidUserName");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyTakenException("InvalidEmail");
        }
        
        User userToSave = new User(user.getName(), user.getAge(), user.getEmail(), user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(userToSave);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Project not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<String> getAllUsernames() {
        return userRepository.findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }
}
