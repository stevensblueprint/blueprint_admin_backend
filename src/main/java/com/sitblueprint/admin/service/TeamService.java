package com.sitblueprint.admin.service;

<<<<<<< HEAD
import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.model.Member;

=======
>>>>>>> fix-issue-42
import java.util.List;

import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.model.Team;

public interface TeamService {
<<<<<<< HEAD
	List<Team> getAllTeams();

	Team getTeamById(Long teamId);

	Team createTeam(Team team);

	Team updateTeam(Team team);

	void deleteTeam(Long teamId);

	Member getTeamLeadById(Long teamId);

	Member getProjectManagerById(Long teamId);

	Member getDesignerById(Long teamId);
=======
    List<Team> getAllTeams();
    Team getTeamById(Long teamId);
    Team createTeam(Team team);
    Team updateTeam(Team team);
    void deleteTeam(Long teamId);
    Member getTeamLeadById(Long teamId);
    Member getProjectManagerById(Long teamId);
    Member getDesignerById(Long teamId);

    boolean isEBoardMember(Long memberId);
>>>>>>> fix-issue-42
}
