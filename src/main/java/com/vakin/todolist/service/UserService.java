package com.vakin.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vakin.todolist.dto.UserDto;
import com.vakin.todolist.exception.EmailAlreadyTakenException;
import com.vakin.todolist.exception.UsernameAlreadyTakenException;
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
}
