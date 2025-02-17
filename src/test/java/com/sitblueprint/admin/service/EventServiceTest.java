package com.sitblueprint.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.sitblueprint.admin.model.Event;
import com.sitblueprint.admin.repository.EventRepository;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EventServiceTest {
    
    @Mock
    private EventRepository eventRepository;

    private EventService eventService;

    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    void setUp() {
        eventService = new EventServiceImpl(eventRepository);

        event1 = new Event();
        event1.setId(1L);
        event1.setName("GBM");
        event1.setDate(LocalDate.of(2025, 1, 2)); 
        event1.setLocation("Babbio");

        event2 = new Event();
        event2.setId(2L);
        event2.setName("EBM");
        event2.setDate(LocalDate.of(2025, 1, 3)); // March 5, 2025
        event2.setLocation("Pierce");

        event3 = new Event();
        event3.setId(3L);
        event3.setName("TECH_TEAM_MEETING");
        event3.setDate(LocalDate.of(2025, 1, 10)); // April 10, 2025
        event3.setLocation("Library");
    }

    @Test
    void getEventsWithinDateRange_ShouldReturnOneAndTwo() {
        LocalDate fromDate = LocalDate.of(2025, 1, 1);
        LocalDate toDate = LocalDate.of(2025, 1, 8);

        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2, event3));
        List<Event> result = eventService.getEvents(fromDate, toDate);
        assertNotNull(result);
        assertEquals(2, result.size()); 
        assertEquals("GBM", result.get(0).getName());
        assertEquals("EBM", result.get(1).getName());
    }

    @Test
    void createEvent_ShouldSaveAndReturnEvent() {
        when(eventRepository.save(any(Event.class))).thenReturn(event1);
        Event result = eventService.createEvent(event1);
        assertNotNull(result);
        assertEquals("GBM", result.getName());
        verify(eventRepository).save(any(Event.class));
    }


}
