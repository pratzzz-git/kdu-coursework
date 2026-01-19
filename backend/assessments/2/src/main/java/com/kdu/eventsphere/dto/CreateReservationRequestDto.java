package com.kdu.eventsphere.dto;

public class CreateReservationRequestDto {

    private Long eventId;
    private Integer ticketCount;

    public Long getEventId() { return eventId; }
    public Integer getTicketCount() { return ticketCount; }

    public void setEventId(Long eventId) { this.eventId = eventId; }
    public void setTicketCount(Integer ticketCount) { this.ticketCount = ticketCount; }
}
