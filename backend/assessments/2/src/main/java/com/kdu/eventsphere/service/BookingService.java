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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingService {

    private static final Logger log =
            LoggerFactory.getLogger(BookingService.class);

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

        log.info("Confirming booking for reservation {}", reservationId);

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> {
                    log.error("Reservation {} not found", reservationId);
                    return new RuntimeException("Reservation not found");
                });

        if (!"ACTIVE".equals(reservation.getStatus())) {
            log.error("Reservation {} is not ACTIVE (status={})",
                    reservationId, reservation.getStatus());
            throw new RuntimeException("Reservation cannot be booked");
        }

        Booking booking = new Booking();
        booking.setUser(reservation.getUser());
        booking.setEvent(reservation.getEvent());
        booking.setStatus("CONFIRMED");
        booking.setBookingDate(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);

        log.info("Booking {} created for reservation {}",
                savedBooking.getId(), reservationId);

        Transaction transaction = new Transaction();
        transaction.setBooking(savedBooking);
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);

        log.info("Transaction {} created for booking {}",
                transaction.getTransactionId(), savedBooking.getId());

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

        log.info("Cancelling booking {}", bookingId);

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> {
                    log.error("Booking {} not found", bookingId);
                    return new RuntimeException("Booking not found");
                });


        if (!"CONFIRMED".equals(booking.getStatus())) {
            log.error("Booking {} already cancelled or invalid state {}",
                    bookingId, booking.getStatus());
            throw new RuntimeException("Booking already cancelled");
        }

        booking.setStatus("CANCELLED");
        bookingRepository.save(booking);

        log.info("Booking {} cancelled successfully", bookingId);
    }
}
