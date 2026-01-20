package com.example.railway.service;

import com.example.railway.broker.EventSubscriber;
import com.example.railway.dto.event.TicketBookedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements EventSubscriber<TicketBookedEvent> {

    private static final Logger log =
            LoggerFactory.getLogger(InventoryService.class);

    @Override
    public void handle(TicketBookedEvent event) {

        if (event.getAge() < 0) {
            throw new IllegalArgumentException(
                    "Invalid age: " + event.getAge()
            );
        }

        log.info(
                "Inventory updated: Seat {} occupied for booking {}",
                event.getSeatNumber(),
                event.getBookingId()
        );
    }
}
