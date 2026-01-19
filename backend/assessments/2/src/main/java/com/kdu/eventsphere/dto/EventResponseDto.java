package com.kdu.eventsphere.dto;

public class EventResponseDto {

    private Long id;
    private String name;
    private Integer availableTickets;

    public EventResponseDto(Long id, String name, Integer availableTickets) {
        this.id = id;
        this.name = name;
        this.availableTickets = availableTickets;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public Integer getAvailableTickets() { return availableTickets; }
}
