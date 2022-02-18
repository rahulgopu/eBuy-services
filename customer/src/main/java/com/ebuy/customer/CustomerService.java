package com.ebuy.customer;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .build();
        //todo: check if email valid
        //todo: check if email is not taken
        //todo: check if fraudster
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
                "http://FRAUD/api/v1/fraud-check/{customerId}",
                FraudCheckResponse.class,
                customer.getId()
        );

        if(Objects.nonNull(fraudCheckResponse) && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        //todo: send notification
    }
}
