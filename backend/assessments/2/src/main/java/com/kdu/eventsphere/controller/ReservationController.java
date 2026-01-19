package com.kdu.eventsphere.controller;

import com.kdu.eventsphere.dto.*;
import com.kdu.eventsphere.service.ReservationService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ReservationResponseDto create(
            @RequestBody CreateReservationRequestDto dto) {

        return reservationService.createReservation(dto);
    }

    @PutMapping("/{id}")
    public ReservationResponseDto update(
            @PathVariable Long id,
            @RequestBody UpdateReservationRequestDto dto) {

        return reservationService.updateReservation(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        reservationService.deleteReservation(id);
    }
}