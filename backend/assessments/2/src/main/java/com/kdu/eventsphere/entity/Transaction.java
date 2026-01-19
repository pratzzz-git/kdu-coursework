package com.kdu.eventsphere.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @Column(nullable = false)
    private LocalDateTime transactionDate;

    @OneToOne(optional = false)
    @JoinColumn(name = "booking_id")
    private Booking booking;

    // ===== GETTERS =====
    public Long getId() { return id; }
    public String getTransactionId() { return transactionId; }
    public LocalDateTime getTransactionDate() { return transactionDate; }
    public Booking getBooking() { return booking; }

    // ===== SETTERS =====
    public void setId(Long id) { this.id = id; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
    public void setBooking(Booking booking) { this.booking = booking; }
}
