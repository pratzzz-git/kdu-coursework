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

    // getters and setters
}
