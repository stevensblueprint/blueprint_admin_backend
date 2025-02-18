package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Event;
import com.sitblueprint.admin.repository.EventRepository;
import com.sitblueprint.admin.repository.TeamRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class EventServiceImpl implements EventService {
	private final EventRepository eventRepository;

	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public List<Event> getEvents(LocalDate fromDate, LocalDate toDate) {

		List<Event> beforeFilter = eventRepository.findAll(); // gets all of the events in the repo
		List<Event> afterFilter = new ArrayList<>();

		for (Event e : beforeFilter) {
			if (e.getDate().isEqual(fromDate) || e.getDate().isAfter(fromDate) && (e.getDate().isEqual(toDate) || e.getDate().isBefore(toDate))) {
            	afterFilter.add(e);
        }
		}

		return afterFilter;

	}

	@Override
	public Event createEvent(Event event) {
		return eventRepository.save(event);
	}

}
