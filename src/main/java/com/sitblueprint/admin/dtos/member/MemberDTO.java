package com.sitblueprint.admin.dtos.member;

import com.sitblueprint.admin.model.users.Member;
import com.sitblueprint.admin.model.users.Role;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
	private Long id;
	private String name;
	private String username;
	private String email;
	private boolean isActive;
	private LocalDate dateJoined;
	private Set<RoleDTO> roles;
	TeamSummaryDTO team;

	public Member toEntity() {
		return Member.builder().id(this.id).name(this.name).username(this.username).email(this.email)
				.dateJoined(this.dateJoined).isActive(this.isActive)
				.roles(this.roles.stream()
						.map(roleDTO -> Role.builder().id(roleDTO.getId()).name(roleDTO.getName()).build())
						.collect(Collectors.toSet()))
				.build();
	}
}
