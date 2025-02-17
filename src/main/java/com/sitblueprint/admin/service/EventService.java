package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Event;

import java.time.LocalDate;
import java.util.List;


public interface EventService {
    List<Event> getEvents(LocalDate fromDate, LocalDate toDate);
    Event createEvent(Event event);

    

    

}
