package com.example.railway.dto.response;

public class BookingResponseDTO {

    private String bookingId;
    private String message;

    public BookingResponseDTO(String bookingId, String message) {
        this.bookingId = bookingId;
        this.message = message;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getMessage() {
        return message;
    }
}
