package com.spring.boot.blogProject.BlogProject.service;

import com.spring.boot.blogProject.BlogProject.entity.SuccessMessage;
import com.spring.boot.blogProject.BlogProject.entity.User;
import com.spring.boot.blogProject.BlogProject.errorHandler.UserNotFoundException;
import com.spring.boot.blogProject.BlogProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplements implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public ResponseEntity<SuccessMessage> registerUser(User user) throws UserNotFoundException {
        Optional<User> findUser= Optional.ofNullable(userRepository.findByuserName(user.getUserName()));
        if(!findUser.isPresent()){
             userRepository.save(user);
            return ResponseEntity.ok(new SuccessMessage(HttpStatus.OK,"User registered successfully"));
        }else {
            throw new UserNotFoundException("User already registered");
        }

    }

    @Override
    public ResponseEntity<SuccessMessage> loginUser(User user) throws UserNotFoundException {
        User getUser=userRepository.findByuserName(user.getUserName());
        if(getUser!=null && getUser.getPassword().equals(user.getPassword())){
            String sessionToken=generateSessionToken();
            getUser.setSessionToken(sessionToken);
            userRepository.save(getUser);
            return ResponseEntity.ok(new SuccessMessage(HttpStatus.OK,"Login Successfully"));
        }else {
            throw new UserNotFoundException("Registered user not found");
        }

    }

    @Override
    public  ResponseEntity<SuccessMessage>   logoutUser(User user ) throws UserNotFoundException {
        User getUser=userRepository.findByuserName(user.getUserName());
        if(getUser!=null){
            getUser.setSessionToken(null);
            userRepository.save(getUser);
            return ResponseEntity.ok(new SuccessMessage(HttpStatus.OK,"User Logged Out"));
        }else {
            throw new UserNotFoundException("Cannot logout, User data not available");
        }

    }

    @Override
    public String generateSessionToken() {
        //Some random string for dummy session
        return UUID.randomUUID().toString();
    }

}
