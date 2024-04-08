package com.sitblueprint.admin.repository.events;

import com.sitblueprint.admin.model.events.EventForm;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventFormRepository extends JpaRepository<EventForm, Long> {
    List<EventForm> findByEventId(Long eventId);

    @Transactional
    @Modifying
    @Query("DELETE FROM EventForm e WHERE e.event = :eventId")
    void deleteByEventId(Long eventId);
}
