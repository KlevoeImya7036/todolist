package com.vakin.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vakin.todolist.dto.UserDto;
import com.vakin.todolist.exception.EmailAlreadyTakenException;
import com.vakin.todolist.exception.InvalidAgeException;
import com.vakin.todolist.exception.InvalidPasswordException;
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

    public User registerUser(UserDto user) throws UsernameAlreadyTakenException, EmailAlreadyTakenException, InvalidPasswordException, InvalidAgeException{
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UsernameAlreadyTakenException("Это имя пользователя уже используется. Попробуйте другое.");
        }

        if (user.getPassword().length() < 8) {
            throw new InvalidPasswordException("Пароль должен быть как минимум 8 символов в длинну.");
        }
        
        if (user.getAge() < 18 || user.getAge() > 100) {
            throw new InvalidAgeException("Возраст должен быть от 18 до 100 лет");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EmailAlreadyTakenException("Эта почта уже используется. Попробуйте другую.");
        }
        
        User userToSave = new User();
        userToSave.setName(user.getName());
        userToSave.setAge(user.getAge());
        userToSave.setUsername(user.getUsername());
        userToSave.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userToSave.setEmail(user.getEmail());
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

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
    }
}
