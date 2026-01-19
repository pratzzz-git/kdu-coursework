package com.kdu.eventsphere.controller;

import com.kdu.eventsphere.dto.BookingResponseDto;
import com.kdu.eventsphere.service.BookingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/confirm/{reservationId}")
    public BookingResponseDto confirm(@PathVariable Long reservationId) {
        return bookingService.confirmBooking(reservationId);
    }

    @PostMapping("/cancel/{bookingId}")
    public void cancel(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }
}
