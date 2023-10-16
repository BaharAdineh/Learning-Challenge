package com.challenge.userservice.service;

import com.challenge.userservice.exception.UnauthorizedException;
import com.challenge.userservice.model.User;
import com.challenge.userservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }
/*

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }



    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


    private final TokenService tokenService;

    @Autowired
    public UserService(TokenService tokenService) {
        this.tokenService = tokenService;
    }*/

    public List<User> getAllUsers(@RequestHeader("Authorization") String token) {
        if (tokenService.validateToken(token)) {

            return userRepository.findAll();
        } else {
            // Token is not valid; handle unauthorized access, e.g., throw an exception.
            throw new UnauthorizedException("Invalid or expired token");
        }

        // Your logic here...
    }

    // Other service methods...

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

}