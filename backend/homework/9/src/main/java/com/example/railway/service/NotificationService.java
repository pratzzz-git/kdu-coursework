package com.example.railway.service;

import com.example.railway.broker.EventSubscriber;
import com.example.railway.dto.event.TicketBookedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService implements EventSubscriber<TicketBookedEvent> {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Override
    public void handle(TicketBookedEvent event) {
        log.info("Notification sent for booking {}", event.getBookingId());
    }
}
