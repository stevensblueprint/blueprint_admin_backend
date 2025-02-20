package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Member;
import com.sitblueprint.admin.model.Organization;
import com.sitblueprint.admin.repository.MemberRepository;
import com.sitblueprint.admin.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final MemberRepository memberRepository;

    public OrganizationService(OrganizationRepository organizationRepository, MemberRepository memberRepository) {
        this.organizationRepository = organizationRepository;
        this.memberRepository = memberRepository;
    }

    public Organization createOrganization(Organization organization, Long memberId) {
        return organizationRepository.save(organization);
    }

    public boolean isEBoardMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        return member.isPresent() && member.get().getRoles().stream()
                .anyMatch(role -> "E-BOARD".equals(role.getName()));
    }

    public Organization getOrganizationById(Long id) {
        return organizationRepository.findById(id).orElse(null);
    }
}
