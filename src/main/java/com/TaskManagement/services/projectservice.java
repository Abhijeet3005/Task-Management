package com.TaskManagement.services;

import com.TaskManagement.models.Project;
import com.TaskManagement.repositories.projectrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class projectservice {

    @Autowired
    private projectrepository projectRepo;

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Optional<Project> getProjectById(String id) {
        return projectRepo.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    public void deleteProject(String id) {
        projectRepo.deleteById(id);
    }

    public Project updateProject(String id, Project updatedProject) {
        Optional<Project> existing = projectRepo.findById(id);
        if (existing.isPresent()) {
            Project project = existing.get();
            project.setName(updatedProject.getName());
            project.setDescription(updatedProject.getDescription());
            return projectRepo.save(project);
        }
        return null;
    }
}
