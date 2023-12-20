package com.challenge.orderservice.model;

import com.challenge.productservice.model.Product;
import com.challenge.userservice.model.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String orderId;
    private String productId;
    private String userId;
    private Product product;
    private User user;

}
