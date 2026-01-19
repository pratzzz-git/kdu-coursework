package com.kdu.eventsphere.service;
import com.kdu.eventsphere.entity.Reservation;

import com.kdu.eventsphere.dto.*;
import com.kdu.eventsphere.entity.*;
import com.kdu.eventsphere.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final EventRepository eventRepository;
    private final com.kdu.eventsphere.repository.UserRepository userRepository;

    public ReservationService(ReservationRepository reservationRepository,
                              EventRepository eventRepository,
                              com.kdu.eventsphere.repository.UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public ReservationResponseDto createReservation(String username,
                                                    CreateReservationRequestDto dto) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Event event = eventRepository.findByIdForUpdate(dto.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (event.getAvailableTickets() < dto.getTicketCount()) {
            throw new RuntimeException("Not enough tickets available");
        }

        event.setAvailableTickets(
                event.getAvailableTickets() - dto.getTicketCount()
        );

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setEvent(event);
        reservation.setReservedTickets(dto.getTicketCount());
        reservation.setStatus("ACTIVE");

        eventRepository.save(event);
        Reservation saved = reservationRepository.save(reservation);

        return new ReservationResponseDto(
                saved.getId(),
                event.getId(),
                saved.getReservedTickets(),
                saved.getStatus()
        );
    }


    @Transactional
    public ReservationResponseDto updateReservation(
            Long reservationId,
            UpdateReservationRequestDto dto) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Event event = reservation.getEvent();

        int diff = dto.getTicketCount() - reservation.getReservedTickets();

        if (event.getAvailableTickets() < diff) {
            throw new RuntimeException("Not enough tickets available");
        }

        event.setAvailableTickets(event.getAvailableTickets() - diff);
        reservation.setReservedTickets(dto.getTicketCount());

        eventRepository.save(event);
        Reservation updated = reservationRepository.save(reservation);

        return new ReservationResponseDto(
                updated.getId(),
                event.getId(),
                updated.getReservedTickets(),
                updated.getStatus()
        );
    }

    @Transactional
    public void deleteReservation(Long reservationId) {

        Reservation reservation =
                reservationRepository.findById(reservationId)
                        .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Event event = reservation.getEvent();

        event.setAvailableTickets(
                event.getAvailableTickets() + reservation.getReservedTickets()
        );

        reservationRepository.delete(reservation);
        eventRepository.save(event);
    }
}
