package com.challenge.orderservice.service;

import com.challenge.userservice.service.TokenService;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final TokenService tokenService;

    public OrderService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

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
