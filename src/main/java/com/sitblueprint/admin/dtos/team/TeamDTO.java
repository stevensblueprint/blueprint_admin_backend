package com.sitblueprint.admin.dtos.team;

import com.sitblueprint.admin.dtos.MemberSummaryDTO;
import com.sitblueprint.admin.dtos.OrganizationSummaryDTO;
import com.sitblueprint.admin.model.Team;
import java.time.LocalDate;
import java.util.Set;
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
public class TeamDTO {
	private Long id;
	private String name;
	private OrganizationSummaryDTO organizationSummaryDTO;
	private int memberCount;
	private MemberSummaryDTO teamLead;
	private MemberSummaryDTO projectManager;
	private MemberSummaryDTO designer;
	private LocalDate dateCreated;
	private Set<MemberSummaryDTO> members;

	public Team toEntity() {
		return Team.builder().id(this.id).name(this.name).build();
	}
}
