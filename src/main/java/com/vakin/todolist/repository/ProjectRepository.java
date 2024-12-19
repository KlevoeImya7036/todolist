package com.vakin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vakin.todolist.model.Project;

public interface ProjectRepository  extends JpaRepository<Project, Long>{

}
