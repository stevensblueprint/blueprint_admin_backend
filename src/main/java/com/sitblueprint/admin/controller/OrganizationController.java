package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.model.Organization;
import com.sitblueprint.admin.service.OrganizationService;
import com.sitblueprint.admin.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;
    private final MemberService memberService;

    public OrganizationController(OrganizationService organizationService, MemberService memberService) {
        this.organizationService = organizationService;
        this.memberService = memberService;
    }

    @PostMapping
    public ResponseEntity<?> createOrganization(@RequestBody OrganizationRequest request) {

        if (request.getName() == null || request.getName().isEmpty()) {
            return ResponseEntity.badRequest().body("Organization name is required.");
        }
        if (request.getMemberId() == null) {
            return ResponseEntity.badRequest().body("Member ID is required.");
        }

        boolean isEBoard = organizationService.isEBoardMember(request.getMemberId());
        if (!isEBoard) {
            return ResponseEntity.status(403).body("Only eBoard members can create organizations.");
        }

        Member teamLead = request.getTeamLead() != null ? request.getTeamLead() : null;
        Member projectManager = request.getProjectManager() != null ? request.getProjectManager() : null;

        Organization organization = new Organization();
        organization.setName(request.getName());
        organization.setTeamLead(teamLead);
        organization.setProjectManager(projectManager);

        Organization savedOrganization = organizationService.createOrganization(organization, request.getMemberId());
        return ResponseEntity.ok(savedOrganization);
    }

    static class OrganizationRequest {
        private String name;
        private Long memberId;
        private Member teamLead;
        private Member projectManager;

        public String getName() { return name; }
        public Long getMemberId() { return memberId; }
        public Member getTeamLead() { return teamLead; }
        public Member getProjectManager() { return projectManager; }
    }
}
