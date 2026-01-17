package org.example.library.domain.entity;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private Book book;

    @ManyToOne(optional = false)
    private User borrower;

    @Column(nullable = false)
    private Instant borrowedAt = Instant.now();

    private Instant returnedAt;

    // getters & setters
}
