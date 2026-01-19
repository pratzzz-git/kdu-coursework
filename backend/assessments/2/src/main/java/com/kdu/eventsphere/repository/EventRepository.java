package com.kdu.eventsphere.repository;


import com.kdu.eventsphere.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
