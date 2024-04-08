package com.sitblueprint.admin.service.events;

import com.sitblueprint.admin.model.events.EventForm;
import com.sitblueprint.admin.repository.events.EventFormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventFormServiceImpl implements EventFormService {
    private final EventFormRepository eventFormRepository;

    public EventFormServiceImpl(EventFormRepository eventFormRepository) {
        this.eventFormRepository = eventFormRepository;
    }

    @Override
    public List<EventForm> getAllEventForms() {
        return eventFormRepository.findAll();
    }

    @Override
    public EventForm getEventFormById(Long eventId) {
        return eventFormRepository.findById(eventId).orElse(null);
    }

    @Override
    public List<EventForm> getEventFormsByEventId(Long eventId) {
        return eventFormRepository.findByEventId(eventId);
    }

    @Override
    public EventForm createEventForm(EventForm eventForm) {
        return eventFormRepository.save(eventForm);
    }

    @Override
    public EventForm updateEventForm(EventForm eventForm) {
        return eventFormRepository.saveAndFlush(eventForm);
    }

    @Override
    public void deleteEventFormById(Long eventId) {
        eventFormRepository.deleteById(eventId);
    }

    @Override
    public void deleteEventFormByEventId(Long eventId) {
        eventFormRepository.deleteByEventId(eventId);
    }
}
