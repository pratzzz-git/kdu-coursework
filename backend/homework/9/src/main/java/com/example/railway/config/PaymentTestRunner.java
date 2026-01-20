package com.example.railway.config;

import com.example.railway.broker.EventBroker;
import com.example.railway.dto.event.PaymentEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentTestRunner {

    @Bean
    CommandLineRunner testDuplicatePayments(
            EventBroker<PaymentEvent> paymentEventBroker
    ) {
        return args -> {

            PaymentEvent event = new PaymentEvent(
                    "txn-123",
                    "booking-abc",
                    500.00
            );

            // Simulate at-least-once delivery (duplicate messages)
            paymentEventBroker.publish(event);
            paymentEventBroker.publish(event);
            paymentEventBroker.publish(event);
        };
    }
}
