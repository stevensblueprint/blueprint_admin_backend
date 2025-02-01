package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.model.Member;

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
}
