package com.TaskManagement.services;

import com.TaskManagement.models.Task;
import com.TaskManagement.models.User;
import com.TaskManagement.payload.RegisterRequest;
import com.TaskManagement.repositories.taskrepository;
import com.TaskManagement.repositories.userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.TaskManagement.config.appconfig;
import java.util.List;
import java.util.Optional;

@Service
public class userservice {

    @Autowired
    private userrepository userRepo;

    @Autowired
    private taskrepository taskrepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepo.findById(id);
    }

    public List<User> getUsersByRole(String role) {
        return userRepo.findByRole(role);
    }

    public User createUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole() != null ? request.getRole() : "USER");
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepo.save(user);
    }

    public void deleteUser(String id) {
        userRepo.deleteById(id);
    }

}
