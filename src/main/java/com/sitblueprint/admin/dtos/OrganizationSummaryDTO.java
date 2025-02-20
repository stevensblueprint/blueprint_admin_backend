package com.sitblueprint.admin.dtos;

import com.sitblueprint.admin.model.Organization;
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

	public OrganizationSummaryDTO(Organization org) {
		if(org != null) {
			this.id = org.getId();
			this.name = org.getName();
		}
	}
}
