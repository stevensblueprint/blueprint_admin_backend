package com.sitblueprint.admin.controller.users;

import com.sitblueprint.admin.model.users.Team;
import com.sitblueprint.admin.model.users.Member;
import com.sitblueprint.admin.model.users.Attendance;
import com.sitblueprint.admin.service.users.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/team/")
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

	@PostMapping("attendance")
	public List<Attendance> markTeamAttendance(@RequestParam Long teamId, @RequestParam LocalDateTime date) {
		return teamService.markTeamAttendance(teamId, date);
	}

	@GetMapping("attendance")
	public List<Attendance> getTeamAttendance(@RequestParam Long teamId, @RequestParam LocalDateTime date) {
		return teamService.getTeamAttendance(teamId, date);
	}

	@GetMapping("attendance/all")
	public List<Attendance> getTeamAllAttendance(@RequestParam Long teamId,
			@RequestParam(required = false) LocalDateTime startDate,
			@RequestParam(required = false) LocalDateTime endDate) {
		return teamService.getTeamAllAttendance(teamId, startDate, endDate);
	}

	@PutMapping("attendance")
	public List<Attendance> updateTeamAttendance(@RequestParam Long teamId, @RequestParam LocalDateTime date) {
		return teamService.updateTeamAttendance(teamId, date);
	}

	@DeleteMapping("attendance")
	public void deleteTeamAttendance(@RequestParam Long teamId, @RequestParam LocalDateTime date) {
		teamService.deleteTeamAttendance(teamId, date);
	}
}
