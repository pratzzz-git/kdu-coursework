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
    private String status; // CONFIRMED, CANCELLED

    @Column(nullable = false)
    private LocalDateTime bookingDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    // ===== GETTERS =====
    public Long getId() { return id; }
    public String getStatus() { return status; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public User getUser() { return user; }
    public Event getEvent() { return event; }

    // ===== SETTERS =====
    public void setId(Long id) { this.id = id; }
    public void setStatus(String status) { this.status = status; }
    public void setBookingDate(LocalDateTime bookingDate) { this.bookingDate = bookingDate; }
    public void setUser(User user) { this.user = user; }
    public void setEvent(Event event) { this.event = event; }
}
