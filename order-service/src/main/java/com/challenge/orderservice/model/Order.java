package com.challenge.ivms.orderservice.model;

import com.challenge.ivms.productservice.model.Product;
import com.challenge.ivms.userservice.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String productId;
    private String userId;
    private Product product;
    private User user;

}
