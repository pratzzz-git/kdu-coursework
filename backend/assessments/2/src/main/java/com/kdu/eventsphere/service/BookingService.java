package com.kdu.eventsphere.service;

import com.kdu.eventsphere.dto.BookingResponseDto;

import com.kdu.eventsphere.entity.Booking;
import com.kdu.eventsphere.entity.Event;
import com.kdu.eventsphere.entity.Reservation;
import com.kdu.eventsphere.entity.Transaction;

import com.kdu.eventsphere.repository.BookingRepository;
import com.kdu.eventsphere.repository.ReservationRepository;
import com.kdu.eventsphere.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final ReservationRepository reservationRepository;
    private final TransactionRepository transactionRepository;

    public BookingService(BookingRepository bookingRepository,
                          ReservationRepository reservationRepository,
                          TransactionRepository transactionRepository) {
        this.bookingRepository = bookingRepository;
        this.reservationRepository = reservationRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public BookingResponseDto confirmBooking(Long reservationId) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (!"ACTIVE".equals(reservation.getStatus())) {
            throw new RuntimeException("Reservation cannot be booked");
        }

        Booking booking = new Booking();
        booking.setUser(reservation.getUser());
        booking.setEvent(reservation.getEvent());
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);

        Transaction transaction = new Transaction();
        transaction.setBooking(savedBooking);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        reservation.setStatus("CONFIRMED");
        reservationRepository.save(reservation);

        return new BookingResponseDto(
                savedBooking.getId(),
                savedBooking.getEvent().getId(),
                savedBooking.getStatus(),
                savedBooking.getBookingDate(),
                transaction.getTransactionId()
        );
    }

    @Transactional
    public void cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        if (!"CONFIRMED".equals(booking.getStatus())) {
            throw new RuntimeException("Booking already cancelled");
        }

        Event event = booking.getEvent();

        event.setAvailableTickets(
                event.getAvailableTickets() + booking.getEvent().getAvailableTickets()
        );

        booking.setStatus("CANCELLED");

        bookingRepository.save(booking);
    }
}
