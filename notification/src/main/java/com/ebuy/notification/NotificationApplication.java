package com.ebuy.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {
                "com.ebuy.notification",
                "com.ebuy.amqp",
        }
)
@EnableEurekaClient
@EnableFeignClients(
        basePackages = "com.ebuy.clients.notification"
)
public class NotificationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer, NotificationConfig config) {
//        return args -> {
//            producer.publish(new Person("rahul", 26), config.getInternalExchange(), config.getInternalNotificationRoutingKey());
//        };
//    }
//
//    record Person(String name, int age){}
}
