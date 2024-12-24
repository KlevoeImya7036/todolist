package com.vakin.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vakin.todolist.model.Project;
import com.vakin.todolist.model.User;

public interface ProjectRepository  extends JpaRepository<Project, Long>{
    List<Project> findByUsers (User user);
}