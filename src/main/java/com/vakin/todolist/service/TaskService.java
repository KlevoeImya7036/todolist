package com.vakin.todolist.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vakin.todolist.dto.ProjectDto;
import com.vakin.todolist.dto.TaskDto;
import com.vakin.todolist.exception.ProjectNotFoundException;
import com.vakin.todolist.exception.TaskNotFoundException;
import com.vakin.todolist.model.Project;
import com.vakin.todolist.model.Task;
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.ProjectRepository;
import com.vakin.todolist.repository.TaskRepository;

import jakarta.transaction.Transactional;

@Service
public class TaskService {

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Task saveTask(TaskDto taskdto, Long project_id) {

        Task taskToSave = new Task();
        taskToSave.setName(taskdto.getName());
        taskToSave.setDescription(taskdto.getDescription());
        taskToSave.setDone(false);
        taskToSave.setProject(projectRepository.findById(project_id).orElseThrow(() -> new ProjectNotFoundException("Project not found")));
        return taskRepository.save(taskToSave);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @Transactional
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    @Transactional
    public Task editTask(Long id, TaskDto task) {
        Task taskToEdit = getTaskById(id);
        taskToEdit.setName(task.getName());
        taskToEdit.setDescription(task.getDescription());
        taskToEdit.setDone(task.isDone());
        return taskRepository.save(taskToEdit);
    }

    public Task addAssignedUsers(Long id, Set<User> assigned) {
        Task taskToSave = getTaskById(id);
        taskToSave.addAssignedUser(assigned);
        return taskRepository.save(taskToSave);
    }
}
