package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.dtos.MemberSummaryDTO;
import com.sitblueprint.admin.dtos.OrganizationSummaryDTO;
import com.sitblueprint.admin.dtos.team.TeamDTO;
import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.repository.TeamRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeamServiceImpl implements TeamService {
	private final TeamRepository teamRepository;

	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	@Override
	public Team getTeamById(Long teamId) {
		return teamRepository.findById(teamId).orElse(null);
	}

	@Override
	public Team createTeam(Team team) {
		team.setDateCreated(LocalDate.now());
		return teamRepository.save(team);
	}

	@Override
	public Team updateTeam(Team team) {
		return teamRepository.saveAndFlush(team);
	}

	@Override
	public void deleteTeam(Long teamId) {
		teamRepository.deleteById(teamId);
	}

	@Override
	public Member getTeamLeadById(Long teamId) {
		Optional<Team> optionalTeam = teamRepository.findById(teamId);
		if (optionalTeam.isEmpty()) {
			throw new RuntimeException("Team not found: " + teamId);
		}
		return optionalTeam.get().getTeamLead();
	}

	@Override
	public Member getProjectManagerById(Long teamId) {
		Optional<Team> optionalTeam = teamRepository.findById(teamId);
		if (optionalTeam.isEmpty()) {
			throw new RuntimeException("Team not found: " + teamId);
		}
		return optionalTeam.get().getProjectManager();
	}

	@Override
	public Member getDesignerById(Long teamId) {
		Optional<Team> optionalTeam = teamRepository.findById(teamId);
		if (optionalTeam.isEmpty()) {
			throw new RuntimeException("Team not found: " + teamId);
		}
		return optionalTeam.get().getDesigner();
	}
	// New Method: Fetch Teams by Date
	@Override
	public List<Team> getTeamsByDate(String date) {
		return teamRepository.findByDate(LocalDate.parse(date));
	}

	// New Method: Fetch Team Details by ID
	@Override
	public TeamDTO getTeamDetailsById(Long teamId) {
		Team team = teamRepository.findById(teamId)
				.orElseThrow(() -> new RuntimeException("Team not found: " + teamId));

		// Convert Team entity to TeamDTO
		return new TeamDTO(team.getId(), team.getName(), new OrganizationSummaryDTO(team.getOrganization()), // Assuming
																												// OrganizationSummaryDTO
																												// has a
																												// constructor
																												// that
																												// accepts
																												// Organization
				team.getMembers().size(), new MemberSummaryDTO(team.getTeamLead()), // Assuming MemberSummaryDTO has a
																					// constructor that accepts Member
				new MemberSummaryDTO(team.getProjectManager()), new MemberSummaryDTO(team.getDesigner()),
				team.getDateCreated(),
				team.getMembers().stream().map(MemberSummaryDTO::new).collect(Collectors.toSet()), // Convert members to
																									// MemberSummaryDTOs
				team.getProposalUrl(), team.getDevelopmentEnvUrl(), team.getProductionEnvUrl(),
				team.getAwsConsoleUrl());
	}

}
