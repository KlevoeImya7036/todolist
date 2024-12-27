package com.vakin.todolist.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vakin.todolist.dto.ProjectDto;
import com.vakin.todolist.exception.ProjectNotFoundException;
import com.vakin.todolist.model.Project;
import com.vakin.todolist.model.Task;
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.CommentRepository;
import com.vakin.todolist.repository.ProjectRepository;
import com.vakin.todolist.repository.TaskRepository;
import com.vakin.todolist.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public Project saveProject(ProjectDto project, String username) {
        Project projectToSave = new Project();
        User admin = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        projectToSave.setName(project.getName());
        projectToSave.setDescription(project.getDescription());
        projectToSave.setAdmin(admin);
        projectToSave.addUser(admin);

        return projectRepository.save(projectToSave);
    }

    @Transactional
    public Project editProject(Long id, ProjectDto project) {
        Project projectToEdit = getProjectById(id);
        projectToEdit.setName(project.getName());
        projectToEdit.setDescription(project.getDescription());
        projectToEdit.setAdmin(projectToEdit.getAdmin());
        return projectRepository.save(projectToEdit);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found"));
    }

    @Transactional
    public void deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException("Project not found");
        }
        if (!taskRepository.findAllByProjectId(id).isEmpty()) {
            for (Task task : taskRepository.findAllByProjectId(id)) {
                commentRepository.deleteAllByTaskId(task.getId());
            }
            taskRepository.deleteAllByProjectId(id);
        }
        projectRepository.deleteById(id);
    }

    public Project addUserToProject(Long id, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Project project = getProjectById(id);
        project.addUser(user);
        return projectRepository.save(project);
    }

    public List<Project> getAllProjectsByUser(User user) {
        return projectRepository.findByUsers(user);
    }

    public Set<User> getAllUsersInProject(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ProjectNotFoundException("Project not found")).getUsers();
    }

    public boolean isProjectOwner(Long id, String name) {
        return projectRepository.findById(id).map(project -> project.getAdmin().getUsername().equals(name)).orElse(false);
    }

    public boolean isUserInProject(Long id, String username) {
        return projectRepository.findById(id).map(project -> project.getUsers().stream().anyMatch(user -> user.getUsername().equals(username))).orElse(false);
    }
}
