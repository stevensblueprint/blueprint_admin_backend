package com.sitblueprint.admin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sitblueprint.admin.model.Event;
import com.sitblueprint.admin.service.EventService;

@RestController
@RequestMapping("/api/v1/events/")
public class EventController {
    
    @Autowired
    EventService eventService;

    @GetMapping
    public List<Event> getEventsWithin(@RequestParam String page, @RequestParam String from, @RequestParam String to) {
        return eventService.getEventsWithin(Long.parseLong(page), LocalDate.parse(from), LocalDate.parse(to));
    }

    @PostMapping
    public Event createEvent(@RequestBody Event event, @RequestParam String role) {
        if(!role.equals("E-BOARD")) {
            throw new IllegalArgumentException("Must be E-board member");
        }
        return eventService.createEvent(event);
    }
}
