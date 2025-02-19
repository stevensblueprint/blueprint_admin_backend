package com.sitblueprint.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sitblueprint.admin.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
