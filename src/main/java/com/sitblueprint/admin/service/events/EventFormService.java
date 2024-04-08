package com.sitblueprint.admin.service.events;

import com.sitblueprint.admin.model.events.EventForm;

import java.util.List;

public interface EventFormService {
    List<EventFormService> getAllEventForms();

    EventForm getEventFormServiceById(Long eventId);

    List<EventForm> getEventFormsByEventId(Long eventId);

    EventForm createEventForm(EventForm eventForm);

    EventForm updateEventForm(EventForm eventForm);

    void deleteEventForm(EventForm eventForm);
}
