package com.TaskManagement.repositories;

import com.TaskManagement.models.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface taskrepository extends MongoRepository<Task, String> {
    // You can define custom queries here if needed
    List < Task > findByStatus(String status);
    List < Task > findByAssignedTo(String assignedTo);
}