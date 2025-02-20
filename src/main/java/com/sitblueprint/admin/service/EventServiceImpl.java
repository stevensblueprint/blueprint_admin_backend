package com.sitblueprint.admin.service;

import com.sitblueprint.admin.dtos.event.EventDTO;
import com.sitblueprint.admin.model.Event;
import com.sitblueprint.admin.repository.EventRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EventServiceImpl implements EventService {
	private static final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);
	private final EventRepository eventRepository;

	@Autowired
	public EventServiceImpl(EventRepository eventRepository) {
		this.eventRepository = eventRepository;
	}

	@Override
	public List<EventDTO> getEventByDate(int pageNum, LocalDate from, LocalDate to) {
		if (from == null || to == null) {
			throw new IllegalArgumentException("Both 'from' and 'to' dates must be provided.");
		}
		if (from.isAfter(to)) {
			throw new IllegalArgumentException("'From' date cannot be after 'To' date.");
		}

		int pageSize = 10; // You can change this or make it dynamic
		PageRequest pageRequest = PageRequest.of(pageNum, pageSize);
		return eventRepository.findByDateBetweenOrderByDateAsc(from, to, pageRequest).stream().map(Event::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public EventDTO createEvent(EventDTO event) {
		Event savedEvent = eventRepository.save(event.toEntity());
		return savedEvent.toDTO();
	}
}
