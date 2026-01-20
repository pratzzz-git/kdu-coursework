package com.example.railway.controller;

import com.example.railway.dto.request.BookingRequestDTO;
import com.example.railway.dto.response.BookingResponseDTO;
import com.example.railway.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    public BookingResponseDTO book(@Valid @RequestBody BookingRequestDTO request) {
        String bookingId = service.book(request);
        return new BookingResponseDTO(
                bookingId,
                "Booking in progress"
        );
    }
}
