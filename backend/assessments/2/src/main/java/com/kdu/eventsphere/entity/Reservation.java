package com.kdu.eventsphere.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer reservedTickets;

    @Column(nullable = false)
    private String status; // ACTIVE, CANCELLED, CONFIRMED

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    // ===== GETTERS =====
    public Long getId() { return id; }
    public Integer getReservedTickets() { return reservedTickets; }
    public String getStatus() { return status; }
    public User getUser() { return user; }
    public Event getEvent() { return event; }

    // ===== SETTERS =====
    public void setId(Long id) { this.id = id; }
    public void setReservedTickets(Integer reservedTickets) { this.reservedTickets = reservedTickets; }
    public void setStatus(String status) { this.status = status; }
    public void setUser(User user) { this.user = user; }
    public void setEvent(Event event) { this.event = event; }
}
