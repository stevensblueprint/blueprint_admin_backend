package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.Team;
import com.sitblueprint.admin.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
