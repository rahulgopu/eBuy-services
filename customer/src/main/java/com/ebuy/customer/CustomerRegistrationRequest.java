package com.ebuy.customer;

public record CustomerRegistrationRequest(
        String firstname,
        String lastname,
        String email) {
}
