package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.model.Team;
<<<<<<< HEAD
import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.repository.TeamRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

=======
import com.sitblueprint.admin.repository.MemberRepository;
import com.sitblueprint.admin.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
>>>>>>> fix-issue-42
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
<<<<<<< HEAD
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
=======
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    public TeamServiceImpl(TeamRepository teamRepository, MemberRepository memberRepository) {
        this.teamRepository = teamRepository;
        this.memberRepository = memberRepository;
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
>>>>>>> fix-issue-42

	@Override
	public Team createTeam(Team team) {
		team.setDateCreated(LocalDate.now());
		return teamRepository.save(team);
	}

	@Override
	public Team updateTeam(Team team) {
		return teamRepository.saveAndFlush(team);
	}

<<<<<<< HEAD
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
=======
    @Override
    public Member getTeamLeadById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        return optionalTeam.map(Team::getTeamLead).orElse(null);
    }

    @Override
    public Member getProjectManagerById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        return optionalTeam.map(Team::getProjectManager).orElse(null);
    }

    @Override
    public Member getDesignerById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        return optionalTeam.map(Team::getDesigner).orElse(null);
    }

    @Override
    public boolean isEBoardMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member.isPresent() && member.get().getRoles().stream()
                .anyMatch(role -> "E-BOARD".equals(role.getName()));
    }
>>>>>>> fix-issue-42
}
