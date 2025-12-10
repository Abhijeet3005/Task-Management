package com.TaskManagement.controllers;

import com.TaskManagement.models.User;
import com.TaskManagement.payload.RegisterRequest;
import com.TaskManagement.payload.loginrequest;
import com.TaskManagement.payload.jwtresponse;
import com.TaskManagement.services.jwtservice;
import com.TaskManagement.services.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class authcontroller {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtservice jwtService;

    @Autowired
    private userservice userService;

    @PostMapping("/login")
    public jwtresponse authenticateUser(@RequestBody loginrequest loginRequest) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        loginRequest.getUsername(),
//                        loginRequest.getPassword()
//                )
//        );
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        String jwt = jwtService.generateToken(loginRequest.getUsername());
//        return new jwtresponse(jwt);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Fetch user from DB to get userId
        User user = userService.getUserByUsername(loginRequest.getUsername());
        // Generate JWT containing only userId + username
        String jwt = jwtService.generateToken(user.getId(), user.getUsername());

        return new jwtresponse(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest request) {
        User savedUser = userService.createUser(request);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
