package com.sitblueprint.admin.service;

import java.time.LocalDate;
import java.util.List;

import com.sitblueprint.admin.model.Event;

public interface EventService {
    List<Event> getEventsWithin(Long page, LocalDate fromDate, LocalDate toDate);

    Event createEvent(Event event);
}
