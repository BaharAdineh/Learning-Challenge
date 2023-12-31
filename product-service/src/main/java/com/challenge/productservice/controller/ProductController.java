package com.challenge.productservice.controller;

import com.challenge.productservice.service.ProductService;
import com.challenge.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        final List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(final @PathVariable String productId) {
        final Optional<Product> product = productService.getProductById(productId);
        return product.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(final @RequestBody Product product) {
        final Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(final @PathVariable String productId, final @RequestBody Product product) {
        final Optional<Product> updatedProduct = productService.getProductById(productId);
        ResponseEntity<Product> productResponseEntity;
        if (updatedProduct.isPresent()) {
            final Product savedProduct = productService.updateProduct(product);
            productResponseEntity = new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } else {
            productResponseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return productResponseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(final @PathVariable String productId) {
        final Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            productService.deleteProductById(productId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
