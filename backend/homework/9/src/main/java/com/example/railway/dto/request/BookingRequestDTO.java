package com.example.railway.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class BookingRequestDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String seatNumber;

    @Min(0)
    private int age;

    public String getUserId() {
        return userId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public int getAge() {
        return age;
    }
}
