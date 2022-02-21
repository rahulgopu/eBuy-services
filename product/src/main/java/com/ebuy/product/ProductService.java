package com.ebuy.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public record ProductService(ProductRepository productRepository) {

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(Integer productId) {
        return productRepository.findById(productId);
    }
}
