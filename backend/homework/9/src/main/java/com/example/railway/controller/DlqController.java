package com.example.railway.controller;

import com.example.railway.broker.DeadLetterQueue;
import com.example.railway.broker.EventBroker;
import com.example.railway.broker.SimpleEventBroker;
import com.example.railway.dto.event.TicketBookedEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/internal/dlq")
public class DlqController {

    private final SimpleEventBroker<TicketBookedEvent> broker;

    public DlqController(EventBroker<TicketBookedEvent> broker) {
        // safe cast because we KNOW the implementation
        this.broker = (SimpleEventBroker<TicketBookedEvent>) broker;
    }

    @GetMapping("/bookings")
    public List<TicketBookedEvent> viewBookingDlq() {
        return broker.getDeadLetterQueue().getAll();
    }
}
