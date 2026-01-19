package com.kdu.eventsphere.dto;

public class CreateEventRequestDto {

    private String name;
    private Integer ticketCount;

    public String getName() { return name; }
    public Integer getTicketCount() { return ticketCount; }

    public void setName(String name) { this.name = name; }
    public void setTicketCount(Integer ticketCount) { this.ticketCount = ticketCount; }
}
