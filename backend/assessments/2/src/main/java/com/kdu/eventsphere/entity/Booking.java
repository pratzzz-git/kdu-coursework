package main.java.com.kdu.eventsphere.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;

    private LocalDateTime bookingDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;
}
