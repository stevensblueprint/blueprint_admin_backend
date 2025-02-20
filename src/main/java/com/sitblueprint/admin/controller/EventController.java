package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.dtos.event.EventDTO;
import com.sitblueprint.admin.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

	EventService eventService;

	@Autowired
	public EventController(EventService eventService) {
		this.eventService = eventService;
	}

	@GetMapping
	public List<EventDTO> getEventByDate(@RequestParam("page") int pageNum, @RequestParam("from") LocalDate from,
			@RequestParam("to") LocalDate to) {
		return eventService.getEventByDate(pageNum, from, to);
	}

	@PostMapping
	public EventDTO createEvent(@RequestBody EventDTO event) {
		return eventService.createEvent(event);
	}
}
