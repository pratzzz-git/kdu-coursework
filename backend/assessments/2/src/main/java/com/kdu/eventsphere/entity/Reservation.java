package main.java.com.kdu.eventsphere.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer reservedTickets;

    private String status;

    @ManyToOne
    private User user;

    @ManyToOne
    private Event event;
}
