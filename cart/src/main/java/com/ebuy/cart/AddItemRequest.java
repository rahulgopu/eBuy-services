package com.ebuy.cart;

public record AddItemRequest(
        String name,
        String price,
        String currency
    ) {
}
