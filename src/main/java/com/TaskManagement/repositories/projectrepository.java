package com.TaskManagement.repositories;

import com.TaskManagement.models.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface projectrepository extends MongoRepository<Project, String> {
    // You can define custom queries here if needed
//    List < Project > findByStatus(String status);
//    List < Project > findByAssignedTo(String assignedTo);
//    List < Task > findBydueDate(LocalDateTime dueDate);
}