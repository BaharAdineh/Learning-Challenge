package com.challenge.ivms.orderservice.repository;

import com.challenge.ivms.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    // Additional custom query methods if needed
}
