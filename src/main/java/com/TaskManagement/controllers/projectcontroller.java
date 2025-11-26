package com.TaskManagement.controllers;

import com.TaskManagement.models.Project;
import com.TaskManagement.services.projectservice;
import com.TaskManagement.repositories.projectrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class projectcontroller {

    @Autowired
    projectservice projectser;

    @GetMapping
    public List<Project> getprojects(){
        return projectser.getAllProjects();
    }

    @GetMapping("/{id}")
    public Optional<Project> getProjectById(@PathVariable String id) {
        return projectser.getProjectById(id);
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectser.createProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable String id, @RequestBody Project updatedProject) {
        return projectser.updateProject(id, updatedProject);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable String id) {
        projectser.deleteProject(id);
    }
}
