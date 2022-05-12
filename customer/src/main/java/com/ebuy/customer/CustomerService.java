package com.ebuy.customer;

import com.ebuy.amqp.RabbitMQMessageProducer;
import com.ebuy.clients.fraud.FraudCheckResponse;
import com.ebuy.clients.fraud.FraudClient;
import com.ebuy.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public record CustomerService(CustomerRepository customerRepository,
                              FraudClient fraudClient,
                              RabbitMQMessageProducer rabbitMQMessageProducer) {
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

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, Welcome to eBuy...", customer.getFirstname())
        );

        rabbitMQMessageProducer.publish(notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key");

    }
}
