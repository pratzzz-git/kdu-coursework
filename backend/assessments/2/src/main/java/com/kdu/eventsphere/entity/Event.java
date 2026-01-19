package main.java.com.kdu.eventsphere.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer availableTickets;

    @Column(nullable = false)
    private Boolean deleted = false;

    // getters and setters
}
