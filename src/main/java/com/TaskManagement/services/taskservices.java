package com.TaskManagement.services;

import com.TaskManagement.models.Task;
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

    public void createTask(Task task) {
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

    public void deleteTask(String id) {
        taskrepo.deleteById(id);
    }

    public List <Task> listtasksforuser(String id){
        return taskrepo.findByAssignedTo(id);
    }

}
