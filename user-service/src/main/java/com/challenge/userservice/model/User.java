package com.challenge.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "users") // Specify the MongoDB collection name
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    // Other user-related fields

    // Lombok-generated getters and setters
}
