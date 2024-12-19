package com.vakin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vakin.todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
