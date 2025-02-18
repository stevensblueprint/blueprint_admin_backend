package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.Event;
import com.sitblueprint.admin.service.EventService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

	@Autowired
	EventService eventService;

	@GetMapping
	public List<Event> getEvents(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
		return eventService.getEvents(fromDate, toDate);
	}

	@PostMapping
	public Event createEvent(@RequestBody Event event, @RequestHeader("Role") String role) {
		if (!role.equals("E-BOARD")) {
			throw new IllegalArgumentException("Only E-Board can create events");

		} else {
			return eventService.createEvent(event);
		}
	}

}
