package com.example.railway.dto.event;

public class TicketBookedEvent {

    private final String bookingId;
    private final String seatNumber;
    private final int age;

    public TicketBookedEvent(String bookingId, String seatNumber, int age) {
        this.bookingId = bookingId;
        this.seatNumber = seatNumber;
        this.age = age;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public int getAge() {
        return age;
    }
}
