package com.vakin.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vakin.todolist.model.Comment;
import com.vakin.todolist.model.Task;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
    public List<Comment> findByTask(Task project);
    public void deleteAllByTaskId(Long id);
}
