package main.java.com.kdu.eventsphere.entity;

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
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private com.kdu.eventsphere.entity.User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    // getters and setters
}
