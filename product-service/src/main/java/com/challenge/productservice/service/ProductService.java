package com.challenge.productservice.service;

import com.challenge.productservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    List<Product> getAllProducts();

    Optional<Product> getProductById(String productId);
    Product createProduct(Product product);
    Product updateProduct(Product product);
    void deleteProductById(String productId);

}
