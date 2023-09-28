package com.challenge.orderservice.orderCreation;

import com.challenge.orderservice.model.Order;
import com.challenge.orderservice.repository.OrderRepository;
import com.challenge.productservice.model.Product;
import com.challenge.productservice.service.ProductService;
import com.challenge.userservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    // Create an order for a given product and user
    public Order createOrder(Order order) {
        Optional<User> user = userService.getUserById(order.getUserId());
        Optional<Product> product = productService.getProductById(order.getProductId());
        if (user.isPresent() && product.isPresent()) {
            return orderRepository.save(order);
        } else {
            return null;
        }
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    // Additional methods for order management as needed
}