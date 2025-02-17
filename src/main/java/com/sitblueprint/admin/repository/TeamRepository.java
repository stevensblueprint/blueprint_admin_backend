package com.sitblueprint.admin.repository;

import com.sitblueprint.admin.model.Team;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	@Query("SELECT t FROM Team t WHERE t.dateCreated = :date") // Explicit query
	List<Team> findByDate(@Param("date") LocalDate date);
}
