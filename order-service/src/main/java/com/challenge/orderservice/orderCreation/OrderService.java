package com.challenge.ivms.orderservice.orderCreation;

import com.challenge.ivms.orderservice.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

     Order createOrder(Order order) ;
     List<Order> getAllOrders();
     Optional<Order> getOrderById(String id);
     void deleteOrder(String id);
}
