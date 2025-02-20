package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Event;
import com.sitblueprint.admin.repository.EventRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventServiceSol {
    private final EventRepo ER;

    public EventServiceSol(EventRepo ER) {
        this.ER = ER;
    }

    public List<Event> getEventsWithin(Long page, LocalDate from, LocalDate to){
        int perpage = 10;
        List<Event> events = new ArrayList<>();

        int c = 0;

        for(Event event: ER.findAll())
        {
            if(c>(page*perpage) + perpage) break;
            LocalDate d = event.getDate();

            if(d.isAfter(from) && d.isBefore(to))
            {
                c++;
                if(c<=(page*perpage)) continue;
                events.add(event);
            }
        }

        return events;
    }

    public Event createEvent(Event event) {

        return ER.save(event);


    }
    
}
