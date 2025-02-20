package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.model.Organization;
import com.sitblueprint.admin.model.Team;
<<<<<<< HEAD
import com.sitblueprint.admin.model.Member;
=======
>>>>>>> fix-issue-42
import com.sitblueprint.admin.service.TeamService;
import com.sitblueprint.admin.service.OrganizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@RestController
<<<<<<< HEAD
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
=======
@RequestMapping("/api/v1/teams")
public class TeamController {

    private final TeamService teamService;
    private final OrganizationService organizationService;

    public TeamController(TeamService teamService, OrganizationService organizationService) {
        this.teamService = teamService;
        this.organizationService = organizationService;
    }

    @PostMapping
    public ResponseEntity<?> createTeam(@RequestBody TeamRequest request) {
        if (request.getName() == null || request.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Team name is required.");
        }
        if (request.getOrganizationId() == null) {
            return ResponseEntity.badRequest().body("Organization ID is required.");
        }
        if (request.getMemberId() == null) {
            return ResponseEntity.badRequest().body("Member ID is required.");
        }

        boolean isEBoard = teamService.isEBoardMember(request.getMemberId());
        if (!isEBoard) {
            return ResponseEntity.status(403).body("Only eBoard members can create teams.");
        }

        Organization organization = organizationService.getOrganizationById(request.getOrganizationId());
        if (organization == null) {
            return ResponseEntity.badRequest().body("Invalid Organization ID.");
        }

        Team team = new Team();
        team.setName(request.getName());
        team.setOrganization(organization);
        team.setTeamLead(request.getTeamLead());
        team.setProjectManager(request.getProjectManager());
        team.setDesigner(request.getDesigner());
        team.setDateCreated(LocalDate.now());
        team.setMembers(request.getMembers() != null ? request.getMembers() : new HashSet<>());

        Team savedTeam = teamService.createTeam(team);
        return ResponseEntity.ok(savedTeam);
    }

    static class TeamRequest {
        private String name;
        private Long organizationId;
        private Long memberId; 
        private Member teamLead;
        private Member projectManager;
        private Member designer;
        private Set<Member> members;

        public String getName() { return name; }
        public Long getOrganizationId() { return organizationId; }
        public Long getMemberId() { return memberId; }
        public Member getTeamLead() { return teamLead; }
        public Member getProjectManager() { return projectManager; }
        public Member getDesigner() { return designer; }
        public Set<Member> getMembers() { return members; }
    }
>>>>>>> fix-issue-42
}
