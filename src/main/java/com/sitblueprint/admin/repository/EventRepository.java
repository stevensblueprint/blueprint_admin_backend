package com.sitblueprint.admin.repository;

import com.sitblueprint.admin.model.Event;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	Page<Event> findByDateBetweenOrderByDateAsc(LocalDate from, LocalDate to, Pageable pageable);
}
