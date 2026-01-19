package com.kdu.eventsphere.service;

import com.kdu.eventsphere.dto.CreateEventRequestDto;
import com.kdu.eventsphere.dto.UpdateEventRequestDto;
import com.kdu.eventsphere.dto.EventResponseDto;
import com.kdu.eventsphere.entity.Event;
import com.kdu.eventsphere.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EventService {

    private static final Logger log =
            LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponseDto createEvent(CreateEventRequestDto dto) {

        log.info("Creating event with name '{}' and ticketCount {}",
                dto.getName(), dto.getTicketCount());

        Event event = new Event();
        event.setName(dto.getName());
        event.setAvailableTickets(dto.getTicketCount());

        Event saved = eventRepository.save(event);

        log.info("Event created successfully with id {}", saved.getId());

        return new EventResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAvailableTickets()
        );
    }

    public EventResponseDto updateEvent(Long eventId, UpdateEventRequestDto dto) {

        log.info("Updating event {} with new ticketCount {}",
                eventId, dto.getTicketCount());

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> {
                    log.error("Event {} not found", eventId);
                    return new RuntimeException("Event not found");
                });

        if (dto.getTicketCount() < 0) {
            log.error("Invalid ticket count {} for event {}",
                    dto.getTicketCount(), eventId);
            throw new RuntimeException("Ticket count cannot be negative");
        }

        event.setAvailableTickets(dto.getTicketCount());
        Event updated = eventRepository.save(event);

        log.info("Event {} updated successfully", updated.getId());

        return new EventResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getAvailableTickets()
        );
    }

    public Page<EventResponseDto> getAvailableEvents(Pageable pageable) {

        log.info("Fetching available events with pagination: {}", pageable);

        return eventRepository.findAll(pageable)
                .map(e -> new EventResponseDto(
                        e.getId(),
                        e.getName(),
                        e.getAvailableTickets()
                ));
    }
}
