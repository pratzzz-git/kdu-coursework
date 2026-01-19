package com.kdu.eventsphere.entity;

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

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAvailableTickets() {
        return availableTickets;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailableTickets(Integer availableTickets) {
        this.availableTickets = availableTickets;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
