package com.sitblueprint.admin.repository;

import com.sitblueprint.admin.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT currentTeam FROM Team WHERE currentTeam.dateCreated = :date")    
    List<Team> getTeamsBySemester(@Param("date") LocalDate date);

    List<Team> findByDateCreatedBetween(LocalDate startDate, LocalDate endDate);
}


