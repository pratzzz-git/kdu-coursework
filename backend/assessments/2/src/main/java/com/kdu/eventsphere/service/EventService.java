package com.kdu.eventsphere.service;

import com.kdu.eventsphere.dto.CreateEventRequestDto;
import com.kdu.eventsphere.dto.UpdateEventRequestDto;
import com.kdu.eventsphere.dto.EventResponseDto;
import com.kdu.eventsphere.entity.Event;
import com.kdu.eventsphere.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public EventResponseDto createEvent(CreateEventRequestDto dto) {

        Event event = new Event();
        event.setName(dto.getName());
        event.setAvailableTickets(dto.getTicketCount());

        Event saved = eventRepository.save(event);

        return new EventResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getAvailableTickets()
        );
    }

    public EventResponseDto updateEvent(Long eventId, UpdateEventRequestDto dto) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (dto.getTicketCount() < 0) {
            throw new RuntimeException("Ticket count cannot be negative");
        }

        event.setAvailableTickets(dto.getTicketCount());
        Event updated = eventRepository.save(event);

        return new EventResponseDto(
                updated.getId(),
                updated.getName(),
                updated.getAvailableTickets()
        );
    }

    public Page<EventResponseDto> getAvailableEvents(Pageable pageable) {

        return eventRepository.findAll(pageable)
                .map(e -> new EventResponseDto(
                        e.getId(),
                        e.getName(),
                        e.getAvailableTickets()
                ));
    }
}
