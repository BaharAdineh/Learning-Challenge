package com.challenge.orderservice.orderCreation;

import com.challenge.orderservice.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

     Order createOrder(Order order) ;
     List<Order> getAllOrders();
     Optional<Order> getOrderById(String id);
     void deleteOrder(String id);
}
