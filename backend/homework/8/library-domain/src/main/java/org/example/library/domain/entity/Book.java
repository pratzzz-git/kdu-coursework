package org.example.library.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.library.domain.enums.BookStatus;

import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private Instant createdAt = Instant.now();
}
