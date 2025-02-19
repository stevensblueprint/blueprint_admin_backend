package com.sitblueprint.admin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sitblueprint.admin.model.Event;
import com.sitblueprint.admin.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getEventsWithin(Long page, LocalDate fromDate, LocalDate toDate) {
        int eventsPerPage = 10; // Change value as needed
        List<Event> withinEvents = new ArrayList<>();
        int count = 0;
        for(Event event : eventRepository.findAll()) {
            if(count > (page*eventsPerPage)+eventsPerPage) { // end if past selected page
                break;
            }
            LocalDate date = event.getDate();
            if(date.isAfter(fromDate) && date.isBefore(toDate)) {
                count++;
                if(count <= (page*eventsPerPage)) { // skip if less than selected page
                    continue;
                }
                withinEvents.add(event);
            }
        }
        return withinEvents;
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }
}
