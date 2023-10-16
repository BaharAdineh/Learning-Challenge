package com.challenge.userservice.controller;

import com.challenge.userservice.model.User;
import com.challenge.userservice.service.TokenService;
import com.challenge.userservice.exception.UnauthorizedException;
import com.challenge.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public UserController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public List<User> getAllUsers(@RequestHeader("Authorization") String token) {
        // Check the validity of the token
        if (tokenService.validateToken(token)) {
            return userService.getAllUsers(token);
        } else {
            // Handle unauthorized access, e.g., return an error response
            throw new UnauthorizedException("Invalid or expired token");
        }
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable String id, @RequestHeader("Authorization") String token) {
        // Check the validity of the token
        if (tokenService.validateToken(token)) {
            return userService.getUserById(id);
        } else {
            // Handle unauthorized access, e.g., return an error response
            throw new UnauthorizedException("Invalid or expired token");
        }
    }

    // Other controller methods can follow a similar pattern

    // Handle unauthorized exceptions using an exception handler
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }
}
