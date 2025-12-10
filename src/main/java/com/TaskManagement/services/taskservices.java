package com.TaskManagement.services;

import com.TaskManagement.models.Task;
import com.TaskManagement.models.User;
import com.TaskManagement.payload.TaskDTO;
import com.TaskManagement.repositories.taskrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class taskservices {

    @Autowired
    private taskrepository taskrepo;

    public List<Task> listalltasks(){
        return taskrepo.findAll();
    }

    public List<Task> getTasksByStatus(String status) {
        return taskrepo.findByStatus(status);
    }

    public List<Task> getTasksByAssignedTo(String assignedTo) {
        return taskrepo.findByAssignedTo(assignedTo);
    }

    public void createTask(TaskDTO taskdto) {
//        taskrepo.save(task);
        Task task = new Task();
        task.setTitle(taskdto.getTitle());
        task.setDescription(taskdto.getDescription());
        taskrepo.save(task);
    }

    public void updateTask(String id, Task updatedTask) {
        Optional<Task> optionalTask = taskrepo.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setAssignedTo(updatedTask.getAssignedTo());
            existingTask.setStatus(updatedTask.getStatus());
            taskrepo.save(existingTask);
        }
    }
    public void assignTaskToUser(String taskId, String userId) {
        Optional<Task> optionalTask = taskrepo.findById(taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setAssignedTo(userId);
            task.setStatus("IN_PROGRESS"); // or "IN_PROGRESS", your choice
            taskrepo.save(task);
        } else {
            throw new RuntimeException("Task not found with ID: " + taskId);
        }
    }

    public void deleteTask(String id) {
        taskrepo.deleteById(id);
    }

    public List <Task> listtasksforuser(String id){
        return taskrepo.findByAssignedTo(id);
    }

}
