package com.ebuy.product;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("api/v1/products")
public record ProductController(ProductService productService) {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping(path = "{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Product getProduct(@PathVariable("productId") Integer productId) {
        return productService.getProduct(productId).get();
    }
}
