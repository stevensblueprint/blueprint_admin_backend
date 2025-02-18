package com.sitblueprint.admin.service;

import com.sitblueprint.admin.dtos.MemberSummaryDTO;
import com.sitblueprint.admin.dtos.OrganizationSummaryDTO;
import com.sitblueprint.admin.dtos.team.TeamDTO;
import com.sitblueprint.admin.model.Organization;
import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.repository.TeamRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {
	private final TeamRepository teamRepository;

	public TeamServiceImpl(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Override
	public TeamDTO getTeamDTOById(Long teamId) {
		Team team = teamRepository.findById(teamId)
				.orElseThrow(() -> new RuntimeException("Team not found: " + teamId));
		return toTeamDTO(team);
	}

	private TeamDTO toTeamDTO(Team team) {
		Organization org = team.getOrganization();
		Member teamLead = team.getTeamLead();
		Member projectManager = team.getProjectManager();
		Member designer = team.getDesigner();

		Set<MemberSummaryDTO> memberSumSet = new HashSet<>();
		for (Member member : team.getMembers()) {
			memberSumSet.add(toMemberSummaryDTO(member)); // Convert each member and add to the set
		}

		return TeamDTO.builder().id(team.getId()).name(team.getName())
				.organizationSummaryDTO(new OrganizationSummaryDTO(org.getId(), org.getName()))
				.memberCount(team.getMembers().size()).teamLead(toMemberSummaryDTO(teamLead))
				.projectManager(toMemberSummaryDTO(projectManager)).designer(toMemberSummaryDTO(designer))
				.dateCreated(team.getDateCreated()).members(memberSumSet).proposalUrl(team.getProposalUrl())
				.developEnvUrl(team.getDevelopEnvUrl()).prodEnvUrl(team.getProdEnvUrl())
				.awsConsoleUrl(team.getAwsConsoleUrl()).build();
	}

	private MemberSummaryDTO toMemberSummaryDTO(Member member) {
		if (member == null) {
			return null;
		}
		return MemberSummaryDTO.builder().id(member.getId()).name(member.getName()).username(member.getUsername())
				.email(member.getEmail()).isActive(member.isActive()).dateJoined(member.getDateJoined())
				.roles(member.getRoles()).build();
	}

	@Override
	public List<Team> getTeamsByDate(LocalDate date) {
		// Logic for filtering teams by date
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();
		LocalDate start;
		LocalDate end;
		if (month < 6) { // If within Jan - May -> spring semester
			start = LocalDate.of(year, Month.JANUARY, 1);
			end = LocalDate.of(year, Month.MAY, 31);
		} else { // June - Dec -> fall semester
			start = LocalDate.of(year, Month.JUNE, 1);
			end = LocalDate.of(year, Month.DECEMBER, 31);
		}
		// Get the teams within the determined range
		return teamRepository.findByDateCreatedBetween(start, end);
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

		if (team.getProposalUrl() == null || team.getProposalUrl().isEmpty()) {
			team.setProposalUrl("No proposal URL provided yet!");
		}
		if (team.getDevelopEnvUrl() == null || team.getDevelopEnvUrl().isEmpty()) {
			team.setDevelopEnvUrl("No development URL provided yet!");
		}
		if (team.getProdEnvUrl() == null || team.getProdEnvUrl().isEmpty()) {
			team.setProdEnvUrl("No production URL provided yet!");
		}
		if (team.getAwsConsoleUrl() == null || team.getAwsConsoleUrl().isEmpty()) {
			team.setAwsConsoleUrl("No AWS console URL provided yet!");
		}
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
}
