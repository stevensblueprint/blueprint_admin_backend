package com.sitblueprint.admin.service;

import com.sitblueprint.admin.service.TeamService;
import main.java.com.sitblueprint.admin.service.SemesterPeriod;
import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.dtos.MemberSummaryDTO;
import com.sitblueprint.admin.dtos.OrganizationSummaryDTO;
import com.sitblueprint.admin.dtos.team.TeamDTO;
import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.repository.TeamRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;
import java.time.Month;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;


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

	@Override
    public List<Team> getTeamsBySemester(LocalDate inputDate) {
        SemesterPeriod period = determineSemesterPeriod(inputDate);
        return teamRepository.findByDateCreatedBetween(period.getStartDate(), period.getEndDate());
    }

    private SemesterPeriod determineSemesterPeriod(LocalDate date) {
        int year = date.getYear();

        LocalDate springStart = LocalDate.of(year, Month.JANUARY, 21);
        LocalDate springEnd = LocalDate.of(year, Month.MAY, 15);

        LocalDate fallStart = LocalDate.of(year, Month.AUGUST, 25);
        LocalDate fallEnd = LocalDate.of(year, Month.DECEMBER, 15);

        if (!date.isBefore(springStart) && !date.isAfter(springEnd)) {
            return new SemesterPeriod(springStart, springEnd);
        }
        if (!date.isBefore(fallStart) && !date.isAfter(fallEnd)) {
            return new SemesterPeriod(fallStart, fallEnd);
        }

        throw new IllegalArgumentException("Date is not within a valid semester period.");
    }

	@Override
	public TeamDTO getTeamDetails(Long teamId) {
		Team team = teamRepository.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));	
		return convertToTeamDTO(team);
	}

	private TeamDTO convertToTeamDTO(Team team) {

		Set<MemberSummaryDTO> memberSet = new HashSet<>();

		for(Member member : team.getMembers()) {
			memberSet.add(new MemberSummaryDTO(member));
		}

		return new TeamDTO(
		team.getId(), 
		team.getName(), 
		new OrganizationSummaryDTO(team.getOrganization()), 
		team.getMembers().size(), 
		new MemberSummaryDTO(team.getTeamLead()), 
		new MemberSummaryDTO(team.getProjectManager()),
		new MemberSummaryDTO(team.getDesigner()), 
		team.getDateCreated(), 
		memberSet, 
		team.getProposalUrl(),
		team.getDevelopmentUrl(), 
		team.getProductionEnvUrl(), 
		team.getAwsConsoleUrl());
	}
}
