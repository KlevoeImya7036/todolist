package com.vakin.todolist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vakin.todolist.dto.ProjectDto;
import com.vakin.todolist.exception.ProjectNotFoundException;
import com.vakin.todolist.model.Project;
import com.vakin.todolist.model.User;
import com.vakin.todolist.repository.ProjectRepository;
import com.vakin.todolist.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class ProjectService {
    
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Project saveProject(ProjectDto project, String username) {

        Project projectToSave = new Project();
        projectToSave.setName(project.getName());
        projectToSave.setDescription(project.getDescription());
        projectToSave.setAdmin(userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found")));


        return projectRepository.save(projectToSave);
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
        projectRepository.deleteById(id);
    }

    @Transactional
    public Project editProject(Long id, ProjectDto project) {
        Project projectToEdit = getProjectById(id);
        projectToEdit.setName(project.getName());
        projectToEdit.setDescription(project.getDescription());
        projectToEdit.setAdmin(projectToEdit.getAdmin());
        return projectRepository.save(projectToEdit);
    }

    public boolean isProjectOwner(Long id, String name) {
        return projectRepository.findById(id).map(project -> project.getAdmin().getUsername().equals(name)).orElse(false);
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
}