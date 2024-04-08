package com.sitblueprint.admin.service.events;

import com.sitblueprint.admin.model.events.EventForm;

import java.util.List;

public interface EventFormService {
    List<EventForm> getAllEventForms();

    EventForm getEventFormServiceById(Long eventFormId);

    List<EventForm> getEventFormsByEventId(Long eventId);

    EventForm createEventForm(EventForm eventForm);

    EventForm updateEventForm(EventForm eventForm);

    void deleteEventFormById(Long eventId);

    void deleteEventFormByEventId(Long event);
}
