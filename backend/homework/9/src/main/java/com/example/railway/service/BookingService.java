package com.example.railway.service;

import com.example.railway.broker.EventBroker;
import com.example.railway.dto.event.TicketBookedEvent;
import com.example.railway.dto.request.BookingRequestDTO;
import com.example.railway.entity.Booking;
import com.example.railway.enums.BookingStatus;
import com.example.railway.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final BookingRepository repository;
    private final EventBroker<TicketBookedEvent> broker;

    public BookingService(BookingRepository repository,
                          EventBroker<TicketBookedEvent> broker) {
        this.repository = repository;
        this.broker = broker;
    }

    public String book(BookingRequestDTO request) {

        Booking booking = new Booking(
                request.getUserId(),
                request.getSeatNumber(),
                request.getAge(),
                BookingStatus.IN_PROGRESS
        );

        repository.save(booking);

        broker.publish(new TicketBookedEvent(
                booking.getId(),
                request.getSeatNumber(),
                request.getAge()
        ));

        return booking.getId();
    }
}
