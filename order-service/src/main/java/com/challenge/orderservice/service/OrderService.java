package com.challenge.orderservice.service;

import com.challenge.userservice.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private TokenService tokenService;

    public void validateToken(String token) {
        boolean isValidToken = tokenService.validateToken(token);

        if (isValidToken) {
            // Token is valid, proceed with necessary actions
            // ...
        } else {
            // Token is invalid, handle accordingly
            // ...
        }
    }
}
