package com.kdu.eventsphere.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private com.kdu.eventsphere.entity.User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private com.kdu.eventsphere.entity.Event event;

    // getters and setters
}
