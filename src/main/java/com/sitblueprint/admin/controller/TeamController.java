package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.dtos.team.TeamDTO;
import com.sitblueprint.admin.model.Organization;
import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.repository.MemberRepository;
import com.sitblueprint.admin.repository.OrganizationRepository;
import com.sitblueprint.admin.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/team/")
public class TeamController {

	@Autowired
	TeamService teamService;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@GetMapping("all")
	public List<Team> getAllTeams() {
		return teamService.getAllTeams();
	}

	@GetMapping
	public Team getTeam(@Param("teamId") String teamId) {
		return teamService.getTeamById(Long.parseLong(teamId));
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

	@PostMapping
	public ResponseEntity<Team> createTeam(@RequestParam Long creatorId, @RequestParam Long organizationId, @RequestBody Team team) {

		Member creator = memberRepository.findById(creatorId)
				.orElseThrow(() -> new NoSuchElementException("Member not found"));

		boolean isEboard = creator.getRoles().stream()
				.anyMatch(role -> role.getName().equalsIgnoreCase("E-BOARD"));

		if (!isEboard) {
			return ResponseEntity.status(403).build(); // Forbidden
		}

		Organization organization = organizationRepository.findById(organizationId)
				.orElseThrow(() -> new NoSuchElementException("Organization not found"));

		team.setOrganization(organization);
		team.setDateCreated(LocalDate.now());

		Team createdTeam = teamService.createTeam(team);
		return ResponseEntity.ok(createdTeam);
	}
}
