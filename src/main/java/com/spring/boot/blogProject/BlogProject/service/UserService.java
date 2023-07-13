package com.spring.boot.blogProject.BlogProject.service;

import com.spring.boot.blogProject.BlogProject.entity.SuccessMessage;
import com.spring.boot.blogProject.BlogProject.entity.User;
import com.spring.boot.blogProject.BlogProject.errorHandler.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public ResponseEntity<SuccessMessage> registerUser(User user) throws UserNotFoundException;

    public ResponseEntity<SuccessMessage> loginUser(User user) throws  UserNotFoundException;

    public ResponseEntity<SuccessMessage>   logoutUser(User user ) throws  UserNotFoundException;

    public String generateSessionToken();

}
