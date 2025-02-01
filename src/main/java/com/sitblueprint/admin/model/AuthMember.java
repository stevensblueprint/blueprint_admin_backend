package com.sitblueprint.admin.model;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthMember {
	String username;
	String displayName;
	String email;
	String password;
	Boolean disabled;
	Set<Role> roles;

	public AuthMember(Member member) {
		this.username = member.getUsername();
		this.displayName = member.getName();
		this.email = member.getEmail();
		this.password = member.getPassword();
		this.disabled = member.isActive();
		this.roles = member.getRoles();
	}
}
