package com.sitblueprint.admin.controller.events;


import com.sitblueprint.admin.model.events.Event;

import com.sitblueprint.admin.service.events.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/event/")
public class EventController {

    @Autowired
    EventsService eventsService;

    @GetMapping("all")
    public List<Event> getAllEvents() {
        return eventsService.getAllEvents();
    }

    @GetMapping
    public Event getEventById(@Param("eventId") String eventId) {
        return eventsService.getEventById(Long.parseLong(eventId));
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventsService.createEvent(event);
    }

    @PutMapping
    public Event updateEvent(@RequestBody Event event) {
        return eventsService.updateEvent(event);
    }

    @DeleteMapping
    public void deleteEvent(String eventId) {
        eventsService.deleteEventById(Long.parseLong(eventId));
    }

}
