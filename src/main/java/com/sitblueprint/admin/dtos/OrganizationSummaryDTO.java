package com.sitblueprint.admin.dtos;

import com.sitblueprint.admin.model.Organization; // Import Organization entity
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationSummaryDTO {
	private Long id;
	private String name;

	// Constructor to initialize DTO from an Organization object
	public OrganizationSummaryDTO(Organization organization) {
		if (organization != null) {
			this.id = organization.getId();
			this.name = organization.getName();
		}
	}
}
