package com.sitblueprint.admin.model.users;

import lombok.*;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
	String username;
	String displayName;
	String email;
	String password;
	Boolean disabled;
	Set<Role> groups;

	public AuthUser(User user) {
		this.username = user.getUsername();
		this.displayName = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.disabled = user.isEnabled();
		this.groups = user.getRoles();
	}
}
