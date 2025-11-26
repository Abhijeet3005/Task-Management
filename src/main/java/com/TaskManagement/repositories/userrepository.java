package com.TaskManagement.repositories;

import com.TaskManagement.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface userrepository extends MongoRepository<User, String> {
    List<User> findByRole(String role);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
//    Optional<User> findByName(String name);
}