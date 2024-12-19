package com.vakin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vakin.todolist.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
