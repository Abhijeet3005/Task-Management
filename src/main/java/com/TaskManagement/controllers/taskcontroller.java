package com.TaskManagement.controllers;

import com.TaskManagement.payload.TaskDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.TaskManagement.services.jwtservice;
import com.TaskManagement.models.Task;
import com.TaskManagement.repositories.taskrepository;
import com.TaskManagement.services.taskservices;
import java.util.List;
import java.util.Optional;

@RestController
public class taskcontroller {

    @Autowired
    taskservices taskservice;

    @Autowired
    jwtservice jwtser;

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

    @GetMapping("tasks/assignee/{assignedTo}")
    @PreAuthorize("hasRole('ADMIN')")
    public List < Task > gettasksbyassignedto(@PathVariable String assignedTo){
        return taskservice.getTasksByAssignedTo(assignedTo);
    }

    @GetMapping("/my-tasks")
    public List<Task> getMyTasks(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7); // Remove "Bearer "
        String userId = jwtser.extractUserId(token);

        return taskservice.getTasksByAssignedTo(userId);
    }

    @PutMapping("/tasks/{taskId}/assign/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public void assignTaskToUser(@PathVariable String taskId, @PathVariable String userId) {
        taskservice.assignTaskToUser(taskId, userId);
    }

//    @PostMapping("/tasks")
//    @PreAuthorize("hasRole('ADMIN')")
//    public void createtask(@RequestBody Task task) {
//        taskservice.createTask(task);
//    }

    @PostMapping("/tasks")
    @PreAuthorize("hasRole('ADMIN')")
    public void createtask(@RequestBody TaskDTO task) {
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
