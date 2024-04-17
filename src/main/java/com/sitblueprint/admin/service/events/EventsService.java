package com.sitblueprint.admin.service.events;


import com.sitblueprint.admin.model.events.Event;

import java.util.List;

public interface EventsService  {

    List<Event> getAllEvents();


    Event getEventById(Long eventID);

    Event createEvent(Event event);
    Event updateEvent(Event event);

    void deleteEventById(Long eventId);


}