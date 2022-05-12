package com.ebuy.cart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/cart")
public record CartController(CartService cartService) {

    @GetMapping("{customerId}")
    public List<CartItem> getCartDetails(@PathVariable("customerId") Integer customerId) {
        return cartService.getCartDetails(customerId);
    }

    @PostMapping("addItemToCart")
    public void addItemToCart(@RequestBody AddItemRequest cartItem) {
        log.info("new request to add item to cart {}", cartItem);
        cartService.addItemToCart(cartItem, Integer.valueOf(1));
    }
}
