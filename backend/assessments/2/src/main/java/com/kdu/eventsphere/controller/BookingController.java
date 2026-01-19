package com.kdu.eventsphere.controller;

import com.kdu.eventsphere.dto.BookingResponseDto;
import com.kdu.eventsphere.service.BookingService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/confirm/{reservationId}")
    public BookingResponseDto confirmBooking(
            @PathVariable("reservationId") Long reservationId) {

        return bookingService.confirmBooking(reservationId);
    }


    @PostMapping("/cancel/{bookingId}")
    public void cancelBooking(
            @PathVariable("bookingId") Long bookingId) {

        bookingService.cancelBooking(bookingId);
    }

}
