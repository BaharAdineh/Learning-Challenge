package com.challenge.orderservice.ordercreation;

import com.challenge.orderservice.model.Order;
import com.challenge.orderservice.repository.OrderRepository;
import com.challenge.productservice.model.Product;
import com.challenge.productservice.service.ProductService;
import com.challenge.userservice.model.User;
import com.challenge.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private UserService userService;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, UserService userService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public Order createOrder(final Order order) {
        final Optional<User> user = userService.getUserById(order.getUserId());
        final Optional<Product> product = productService.getProductById(order.getProductId());
        if (user.isPresent() && product.isPresent()) {
            return orderRepository.save(order);
        } else {
            return null;
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(final String orderId) {
        return orderRepository.findById(orderId);
    }

    public void deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }

}