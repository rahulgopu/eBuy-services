package com.ebuy.cart;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CartService(CartRepository cartRepository) {

    public List<CartItem> getCartDetails(Integer customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    public void addItemToCart(AddItemRequest addItemRequest, Integer customerId) {

        CartItem newCartItem = CartItem.builder()
                .customerId(customerId)
                .name(addItemRequest.name())
                .price(addItemRequest.price())
                .currency(addItemRequest.currency())
                .build();

        cartRepository.save(newCartItem);
    }
}
