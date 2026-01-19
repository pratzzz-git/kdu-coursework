package com.kdu.eventsphere.repository;

import com.kdu.eventsphere.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
