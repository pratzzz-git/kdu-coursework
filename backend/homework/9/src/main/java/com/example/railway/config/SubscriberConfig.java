package com.example.railway.config;

import com.example.railway.broker.EventBroker;
import com.example.railway.dto.event.PaymentEvent;
import com.example.railway.dto.event.TicketBookedEvent;
import com.example.railway.service.InventoryService;
import com.example.railway.service.NotificationService;
import com.example.railway.service.PaymentService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberConfig {

    private final EventBroker<TicketBookedEvent> ticketBroker;
    private final EventBroker<PaymentEvent> paymentBroker;

    private final InventoryService inventoryService;
    private final NotificationService notificationService;
    private final PaymentService paymentService;

    public SubscriberConfig(
            EventBroker<TicketBookedEvent> ticketBroker,
            EventBroker<PaymentEvent> paymentBroker,
            InventoryService inventoryService,
            NotificationService notificationService,
            PaymentService paymentService
    ) {
        this.ticketBroker = ticketBroker;
        this.paymentBroker = paymentBroker;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
        this.paymentService = paymentService;
    }

    @PostConstruct
    public void registerSubscribers() {

        // Exercise 1 subscribers
        ticketBroker.subscribe(inventoryService);
        ticketBroker.subscribe(notificationService);

        // Exercise 3 subscriber
        paymentBroker.subscribe(paymentService);
    }
}
