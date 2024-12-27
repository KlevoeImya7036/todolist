package com.vakin.todolist.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vakin.todolist.dto.TaskDto;
import com.vakin.todolist.exception.ProjectNotFoundException;
import com.vakin.todolist.exception.TaskNotFoundException;
import com.vakin.todolist.model.Task;
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.CommentRepository;
import com.vakin.todolist.repository.ProjectRepository;
import com.vakin.todolist.repository.TaskRepository;


@Service
public class TaskService {

    @Autowired
    private ProjectRepository projectRepository;
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserService userService;


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
    public void deleteTasks(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found");
        }
        Task task = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
        commentRepository.deleteAll(task.getComments());
        taskRepository.delete(task);
    }

    @Transactional(readOnly = false)
    public Task editTask(Long id, TaskDto task) {
        Task taskToEdit = getTaskById(id);
        taskToEdit.setName(task.getName());
        taskToEdit.setDescription(task.getDescription());
        taskToEdit.setDone(task.isDone());
        return taskRepository.save(taskToEdit);
    }

    public Task addAssignedUsers(Long id, Set<String> assigned) {
        Task taskToSave = getTaskById(id);
        Set<User> users = new HashSet<User>(assigned.stream().map(user -> userService.getUserByUsername(user)).toList());
        taskToSave.setAssigned(users);
        return taskRepository.save(taskToSave);
    }

    public Task completeTask(Long id, boolean done) {
        Task taskToSave = getTaskById(id);
        taskToSave.setDone(done);
        return taskRepository.save(taskToSave);
    }
}
