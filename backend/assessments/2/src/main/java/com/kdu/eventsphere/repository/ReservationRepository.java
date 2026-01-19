package com.kdu.eventsphere.repository;

import com.kdu.eventsphere.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
