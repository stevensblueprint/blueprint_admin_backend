package com.sitblueprint.admin.model;

import com.sitblueprint.admin.dtos.member.MemberDTO;
import com.sitblueprint.admin.dtos.OrganizationSummaryDTO;
import com.sitblueprint.admin.dtos.member.RoleDTO;
import com.sitblueprint.admin.dtos.TeamSummaryDTO;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Member {
	private static final Map<String, Integer> roleRanking = Map.of("E-BOARD", 1, "TEAM_LEAD", 2, "PRODUCT_MANAGER", 3,
			"DEVELOPER", 3, "DESIGNER", 4, "BLUEPRINT_INTERNAL_TEAM", 5);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String githubUsername;

	@Column
	private String classStanding;

	@Column(nullable = false)
	private boolean isActive;

	@Column(nullable = false)
	private LocalDate dateJoined;

	@ManyToMany
	@JoinTable(name = "member_roles", joinColumns = @JoinColumn(name = "member_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	public MemberDTO toDTO() {
		MemberDTO memberDTO = MemberDTO.builder().id(this.id).name(this.name).username(this.username).email(this.email)
				.dateJoined(this.dateJoined).isActive(this.isActive).build();
		Set<RoleDTO> roleDTOS = this.roles.stream()
				.map(role -> RoleDTO.builder().id(role.getId()).name(role.getName()).build())
				.collect(Collectors.toSet());
		memberDTO.setRoles(roleDTOS);

		if (this.team != null) {
			TeamSummaryDTO teamSummaryDTO = TeamSummaryDTO.builder().id(this.team.getId()).name(this.team.getName())
					.build();

			if (this.team.getOrganization() != null) {
				OrganizationSummaryDTO organizationSummaryDTO = OrganizationSummaryDTO.builder()
						.id(this.team.getOrganization().getId()).name(this.team.getOrganization().getName()).build();
				teamSummaryDTO.setOrganization(organizationSummaryDTO);
			}
			memberDTO.setTeam(teamSummaryDTO);
		}
		return memberDTO;
	}

	private String getHighestRankedRole() {
		return this.roles.stream().max(Comparator.comparingInt(roleRanking::get)).map(Role::getName).orElse(null);
	}
}
