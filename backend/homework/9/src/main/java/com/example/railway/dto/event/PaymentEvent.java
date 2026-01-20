package com.example.railway.dto.event;

public class PaymentEvent {

    private final String transactionId;
    private final String bookingId;
    private final double amount;

    public PaymentEvent(String transactionId, String bookingId, double amount) {
        this.transactionId = transactionId;
        this.bookingId = bookingId;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public double getAmount() {
        return amount;
    }
}
