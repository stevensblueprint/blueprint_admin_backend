package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.model.User;
import com.sitblueprint.admin.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/team")
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

    @GetMapping
    public User getTeamLeadById(@Param("teamId") String teamId) {
        return teamService.getTeamLeadById(Long.parseLong(teamId));
    }

    @GetMapping
    public User getProductManagerById(@Param("productId") String productId) {
        return teamService.getProductManagerById(Long.parseLong(productId));
    }
}
