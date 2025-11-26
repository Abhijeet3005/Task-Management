package com.TaskManagement.controllers;

import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.TaskManagement.models.Task;
import com.TaskManagement.repositories.taskrepository;
import com.TaskManagement.services.taskservices;
import java.util.List;
import java.util.Optional;

@RestController
public class taskcontroller {

    @Autowired
    taskservices taskservice;

    @GetMapping
    public List < Task > getavailtasks(){
        return taskservice.getTasksByAssignedTo("null");
    }

    @GetMapping("/tasks")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Task> listalltasks() {
        return taskservice.listalltasks();
    }

    @GetMapping("/tasks/status/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Task> getTasksByStatus(@PathVariable String status) {
        return taskservice.getTasksByStatus(status);
    }

    @GetMapping("/{id}/tasks")
    @PreAuthorize("hasRole('ADMIN')")
    List <Task> gettasksforuser(@PathVariable String id){
        return taskservice.listtasksforuser(id);
    }

    @GetMapping("tasks/assignee/assignedTo")
    @PreAuthorize("hasRole('ADMIN')")
    public List < Task > gettasksbyassignedto(@PathVariable String assignedTo){
        return taskservice.getTasksByAssignedTo(assignedTo);
    }


    @PostMapping("/tasks")
    @PreAuthorize("hasRole('ADMIN')")
    public void createtask(@RequestBody Task task) {
        taskservice.createTask(task);
    }

    @PutMapping("/tasks/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void updatetask(@PathVariable String id, @RequestBody Task updatedTask) {
        taskservice.updateTask(id, updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deletetask(@PathVariable String id) {
        taskservice.deleteTask(id);
    }
}
