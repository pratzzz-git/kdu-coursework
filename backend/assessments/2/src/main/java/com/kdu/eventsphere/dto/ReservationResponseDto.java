package com.kdu.eventsphere.dto;

public class ReservationResponseDto {

    private Long reservationId;
    private Long eventId;
    private Integer reservedTickets;
    private String status;

    public ReservationResponseDto(Long reservationId,
                                  Long eventId,
                                  Integer reservedTickets,
                                  String status) {
        this.reservationId = reservationId;
        this.eventId = eventId;
        this.reservedTickets = reservedTickets;
        this.status = status;
    }

    public Long getReservationId() { return reservationId; }
    public Long getEventId() { return eventId; }
    public Integer getReservedTickets() { return reservedTickets; }
    public String getStatus() { return status; }
}
