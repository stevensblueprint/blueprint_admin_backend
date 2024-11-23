package com.sitblueprint.admin.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamSummaryDTO {
	private Long id;
	private String name;
	private OrganizationSummaryDTO organization;
}
