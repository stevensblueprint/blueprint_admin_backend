package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.service.OrganizationService;
import com.sitblueprint.admin.model.Organization;
import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/organization/")
public class OrganizationController {

    @Autowired
    OrganizationService organizationService;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping
    public ResponseEntity<Organization> createOrganization(@RequestParam Long creatorId, @RequestBody Organization organization) {
        //validating member is part of the e-board
        Member creator = memberRepository.findById(creatorId)
                .orElseThrow(() -> new NoSuchElementException("Member not found."));

        boolean isEboard = creator.getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase("E-BOARD"));

        if(!isEboard) {
            return ResponseEntity.status(403).build();
        }

        Organization createdOrganization = organizationService.createOrganization(organization);
        return ResponseEntity.ok(createdOrganization);
    }
}
