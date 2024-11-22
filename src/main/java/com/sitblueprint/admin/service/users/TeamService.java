package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Attendance;
import com.sitblueprint.admin.model.users.Team;
import com.sitblueprint.admin.model.users.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TeamService {
	List<Team> getAllTeams();

	Team getTeamById(Long teamId);

	Team createTeam(Team team);

	Team updateTeam(Team team);

	void deleteTeam(Long teamId);

	User getTeamLeadById(Long teamId);

	User getProductManagerById(Long teamId);

    List<Attendance> markTeamAttendance(Long teamId, LocalDateTime date);

    List<Attendance> getTeamAttendance(Long teamId, LocalDateTime date);

    List<Attendance> getTeamAllAttendance(Long teamId, LocalDateTime startDate, LocalDateTime endDate);

    List<Attendance> updateTeamAttendance(Long teamId, LocalDateTime date);

    void deleteTeamAttendance(Long teamId, LocalDateTime date);
}
