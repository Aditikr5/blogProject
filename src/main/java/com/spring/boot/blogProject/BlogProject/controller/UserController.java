package com.spring.boot.blogProject.BlogProject.controller;

import com.spring.boot.blogProject.BlogProject.entity.SuccessMessage;
import com.spring.boot.blogProject.BlogProject.entity.User;
import com.spring.boot.blogProject.BlogProject.errorHandler.UserNotFoundException;
import com.spring.boot.blogProject.BlogProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<SuccessMessage> registerUser(@RequestBody User user) throws UserNotFoundException {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessMessage> loginUser(@RequestBody User user )throws UserNotFoundException {
        return userService.loginUser(user);
    }
    @PostMapping("/logout")
    public ResponseEntity<SuccessMessage> logoutUser(@RequestBody User user  )throws UserNotFoundException {
        return userService.logoutUser(user);
    }

}
