package com.sitblueprint.admin.repository;

import com.sitblueprint.admin.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import  java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    List<Team> findByDateCreatedBetween(LocalDate start, LocalDate end);
}
