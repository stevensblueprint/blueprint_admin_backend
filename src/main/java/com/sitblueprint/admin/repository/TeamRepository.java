package com.sitblueprint.admin.repository;

import com.sitblueprint.admin.dtos.team.TeamDTO;
import com.sitblueprint.admin.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	@Query("SELECT team FROM Team team WHERE team.dateCreated BETWEEN :start AND :end")
	List<Team> findByDateCreatedBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
