package com.vakin.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vakin.todolist.model.Comment;
import com.vakin.todolist.model.Task;
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.CommentRepository;
import com.vakin.todolist.dto.CommentDto;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void saveComment(CommentDto comment, User user, Task task) {
        Comment commentToSave = new Comment();
        commentToSave.setText(comment.getText());
        commentToSave.setUser(user);
        commentToSave.setTask(task);
        commentRepository.save(commentToSave);
    }

    @Transactional
    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }

    public List<Comment> getAllByTask(Task project) {
        return commentRepository.findByTask(project);
    }

    @Transactional
    public void deleteAllByTaskId(Long id) {
        commentRepository.deleteByTaskId(id);
    }
}
