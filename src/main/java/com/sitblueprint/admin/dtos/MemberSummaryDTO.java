package com.sitblueprint.admin.dtos;

import com.sitblueprint.admin.model.users.Role;
import java.time.LocalDate;
import java.util.Set;
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
public class MemberSummaryDTO {
	private Long id;
	private String name;
	private String username;
	private String email;
	private boolean isActive;
	private LocalDate dateJoined;
	private Set<Role> roles;
}
