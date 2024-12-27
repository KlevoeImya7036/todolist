package com.vakin.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.vakin.todolist.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Modifying
    @Query(value = "DELETE FROM comments WHERE id = ?1", nativeQuery = true)
    void deleteById(long id);
}