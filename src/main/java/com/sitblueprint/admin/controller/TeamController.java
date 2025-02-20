package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.dtos.MemberSummaryDTO;
import com.sitblueprint.admin.dtos.OrganizationSummaryDTO;
import com.sitblueprint.admin.dtos.team.TeamDTO;
import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/team/")
public class TeamController {

	private final TeamService teamService;

	@Autowired
	public TeamController(TeamService teamService) {
		this.teamService = teamService;
	}

	@GetMapping("byDate/{date}")
	public List<TeamDTO> getTeamsBySemester(@PathVariable("date") String date) {
		LocalDate semesterDate = LocalDate.parse(date);
		List<Team> teams = teamService.getTeamsBySemester(semesterDate);

		List<TeamDTO> teamDTOs = teams.stream()
				.map(team -> TeamDTO.builder().id(team.getId()).name(team.getName()).build())
				.collect(Collectors.toList());

		return teamDTOs;
	}

	@GetMapping("/{teamId}")
	public TeamDTO getTeamDetails(@PathVariable("teamId") Long teamId) {
		Team team = teamService.getTeamById(teamId);

		if (team == null) {
			return null;
		}

		return TeamDTO.builder().id(team.getId()).name(team.getName()).build();
	}

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
