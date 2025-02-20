package com.sitblueprint.admin.repository;

import com.sitblueprint.admin.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepo extends JpaRepository<Event, Long> {
}
