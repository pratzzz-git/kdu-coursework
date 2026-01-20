package com.example.railway.entity;

import com.example.railway.enums.BookingStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String userId;
    private String seatNumber;
    private int age;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    protected Booking() {}

    public Booking(String userId, String seatNumber, int age, BookingStatus status) {
        this.userId = userId;
        this.seatNumber = seatNumber;
        this.age = age;
        this.status = status;
    }

    public String getId() {
        return id;
    }
}
