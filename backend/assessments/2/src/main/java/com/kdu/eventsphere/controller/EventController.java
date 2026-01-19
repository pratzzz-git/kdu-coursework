package com.kdu.eventsphere.controller;

import com.kdu.eventsphere.dto.CreateEventRequestDto;
import com.kdu.eventsphere.dto.UpdateEventRequestDto;
import com.kdu.eventsphere.dto.EventResponseDto;
import com.kdu.eventsphere.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // ADMIN
    @PostMapping("/admin")
    public EventResponseDto createEvent(@RequestBody CreateEventRequestDto dto) {
        return eventService.createEvent(dto);
    }

    // ADMIN
    @PutMapping("/admin/{eventId}")
    public EventResponseDto updateEvent(
            @PathVariable Long eventId,
            @RequestBody UpdateEventRequestDto dto) {
        return eventService.updateEvent(eventId, dto);
    }

    // USER
    @GetMapping
    public Page<EventResponseDto> listEvents(Pageable pageable) {
        return eventService.getAvailableEvents(pageable);
    }
}
