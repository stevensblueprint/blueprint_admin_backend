package com.sitblueprint.admin.service;

import com.sitblueprint.admin.dtos.OrganizationSummaryDTO;
import com.sitblueprint.admin.model.Organization;
import com.sitblueprint.admin.repository.OrganizationRepository;
import com.sitblueprint.admin.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService{
    private final OrganizationRepository organizationRepository;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public Organization createOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }
}
