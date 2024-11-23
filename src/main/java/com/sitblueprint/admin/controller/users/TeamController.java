package com.sitblueprint.admin.controller.users;

import com.sitblueprint.admin.model.users.Team;
import com.sitblueprint.admin.model.users.Member;
import com.sitblueprint.admin.service.users.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team/")
public class TeamController {

	@Autowired
	TeamService teamService;

	@GetMapping("all")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@GetMapping
	public Team getTeam(@Param("teamId") String teamId) {
		return teamService.getTeamById(Long.parseLong(teamId));
	}

	@PostMapping
	public Team createTeam(@RequestBody Team team) {
		return teamService.createTeam(team);
	}

	@PutMapping
	public Team updateTeam(@RequestBody Team team) {
		return teamService.updateTeam(team);
	}

	@DeleteMapping
	public void deleteTeam(String teamId) {
		teamService.deleteTeam(Long.parseLong(teamId));
	}

	@GetMapping("teamLead/{teamId}")
	public Member getTeamLeadById(@PathVariable("teamId") String teamId) {
		return teamService.getTeamLeadById(Long.parseLong(teamId));
	}

	@GetMapping("productManager/{teamId}")
	public Member getProductManagerById(@PathVariable("teamId") String teamId) {
		return teamService.getProjectManagerById(Long.parseLong(teamId));
	}

	@GetMapping("designer/{teamId}")
	public Member getDesignerById(@PathVariable("teamId") String teamId) {
		return teamService.getDesignerById(Long.parseLong(teamId));
	}
}
