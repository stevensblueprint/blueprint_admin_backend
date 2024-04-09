package com.sitblueprint.admin.service.events;


import com.sitblueprint.admin.model.events.Event;
import com.sitblueprint.admin.repository.events.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

public class EventsServiceImpl implements EventsService {
    @Autowired
    EventRepository eventRepository;

    public List<Event> getAllEvents() {


        return eventRepository.findAll();
    }


    public Event getEventById(Long eventID) {
        try {
            return eventRepository.findById(eventID).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(Event event) {
        return eventRepository.saveAndFlush(event);
    }

    public void deleteEventById(Long eventId) {
        eventRepository.deleteById(eventId);
    }


}