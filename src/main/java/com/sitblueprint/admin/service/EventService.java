package com.sitblueprint.admin.service;

import com.sitblueprint.admin.dtos.event.EventDTO;
import java.time.LocalDate;
import java.util.List;

public interface EventService {
	List<EventDTO> getEventByDate(int pageNum, LocalDate from, LocalDate to);

	EventDTO createEvent(EventDTO event);
}
