package com.ebuy.customer;

import com.ebuy.clients.fraud.FraudCheckResponse;
import com.ebuy.clients.fraud.FraudClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public record CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate,
                              FraudClient fraudClient) {
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

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(Objects.nonNull(fraudCheckResponse) && fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }
        //todo: send notification
    }
}
