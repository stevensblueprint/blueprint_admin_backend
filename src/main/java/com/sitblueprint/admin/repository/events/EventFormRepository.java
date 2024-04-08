package com.sitblueprint.admin.repository.events;

import com.sitblueprint.admin.model.events.EventForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventFormRepository extends JpaRepository<EventForm, Long> {
}
