package com.challenge.ivms.orderservice.service;

import com.challenge.ivms.userservice.service.UserValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private UserValidationService userValidationService;

    public void validateToken(String token) {
        boolean isValidToken = userValidationService.validateToken(token);

        if (isValidToken) {
            // Token is valid, proceed with necessary actions
            // ...
        } else {
            // Token is invalid, handle accordingly
            // ...
        }
    }
}
