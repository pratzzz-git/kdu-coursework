package com.example.railway.config;

import com.example.railway.broker.EventBroker;
import com.example.railway.dto.event.TicketBookedEvent;
import com.example.railway.service.InventoryService;
import com.example.railway.service.NotificationService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubscriberConfig {

    private final EventBroker<TicketBookedEvent> broker;
    private final InventoryService inventoryService;
    private final NotificationService notificationService;

    public SubscriberConfig(EventBroker<TicketBookedEvent> broker,
                            InventoryService inventoryService,
                            NotificationService notificationService) {
        this.broker = broker;
        this.inventoryService = inventoryService;
        this.notificationService = notificationService;
    }

    @PostConstruct
    public void registerSubscribers() {
        broker.subscribe(inventoryService);
        broker.subscribe(notificationService);
    }
}
