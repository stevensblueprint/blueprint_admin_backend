package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Team;
import com.sitblueprint.admin.model.users.Member;
import com.sitblueprint.admin.repository.users.TeamRepository;
import java.time.LocalDate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        if (!optionalTeam.isPresent()) {
            throw new RuntimeException("Team not found: " + teamId);
        }
        return optionalTeam.get().getTeamLead();
    }

    @Override
    public Member getProjectManagerById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (!optionalTeam.isPresent()) {
            throw new RuntimeException("Team not found: " + teamId);
        }
        return optionalTeam.get().getProjectManager();
    }

    @Override
    public Member getDesignerById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (!optionalTeam.isPresent()) {
            throw new RuntimeException("Team not found: " + teamId);
        }
        return optionalTeam.get().getDesigner();
    }
}
