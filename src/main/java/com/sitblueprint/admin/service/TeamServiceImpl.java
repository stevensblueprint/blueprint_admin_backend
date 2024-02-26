package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.model.User;
import com.sitblueprint.admin.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
