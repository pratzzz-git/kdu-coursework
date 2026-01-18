package org.example.library.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Loan {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(optional = false)
    private Book book;

    private Instant borrowedAt;
    private Instant returnedAt;
}
