package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Attendance;
import com.sitblueprint.admin.model.users.Team;
import com.sitblueprint.admin.model.users.Member;

import java.time.LocalDateTime;
import java.util.List;

public interface TeamService {
	List<Team> getAllTeams();

	Team getTeamById(Long teamId);

	Team createTeam(Team team);

	Team updateTeam(Team team);

	void deleteTeam(Long teamId);

	Member getTeamLeadById(Long teamId);

	Member getProjectManagerById(Long teamId);

	Member getDesignerById(Long teamId);
	Member getProductManagerById(Long teamId);

    List<Attendance> markTeamAttendance(Long teamId, LocalDateTime date);

    List<Attendance> getTeamAttendance(Long teamId, LocalDateTime date);

    List<Attendance> getTeamAllAttendance(Long teamId, LocalDateTime startDate, LocalDateTime endDate);

    List<Attendance> updateTeamAttendance(Long teamId, LocalDateTime date);

    void deleteTeamAttendance(Long teamId, LocalDateTime date);
}
