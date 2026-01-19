package com.kdu.eventsphere.dto;

import java.time.LocalDateTime;

public class BookingResponseDto {

    private Long bookingId;
    private Long eventId;
    private String status;
    private LocalDateTime bookingDate;
    private String transactionId;

    public BookingResponseDto(Long bookingId,
                              Long eventId,
                              String status,
                              LocalDateTime bookingDate,
                              String transactionId) {
        this.bookingId = bookingId;
        this.eventId = eventId;
        this.status = status;
        this.bookingDate = bookingDate;
        this.transactionId = transactionId;
    }

    public Long getBookingId() { return bookingId; }
    public Long getEventId() { return eventId; }
    public String getStatus() { return status; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public String getTransactionId() { return transactionId; }
}
