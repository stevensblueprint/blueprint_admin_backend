package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Team;
import com.sitblueprint.admin.model.users.User;
import com.sitblueprint.admin.repository.users.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    TeamRepository teamRepository;
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId).get();
    }

    @Override
    public Team createTeam(Team team) {
        team.setDateCreated(LocalDateTime.now());
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
    public User getTeamLeadById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (!optionalTeam.isPresent()) {
            throw new RuntimeException("Team not found: " + teamId);
        }
        return optionalTeam.get().getTeamLead();
    }

    @Override
    public User getProductManagerById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (!optionalTeam.isPresent()) {
            throw new RuntimeException("Team not found: " + teamId);
        }
        return optionalTeam.get().getProductManager();
    }
}
