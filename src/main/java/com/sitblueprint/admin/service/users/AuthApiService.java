package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.AuthUser;

public interface AuthApiService {
	// Define Methods to interact with the external API service
	AuthUser createAuthUser(AuthUser authUser);
	AuthUser updateAuthUser(AuthUser authUser);

	AuthUser deleteAuthUser(String username);

	AuthUser disableAuthUser(String username);

	AuthUser enableAuthUser(String username);

	AuthUser resetPasswordAuthUser(String username, String password);
}
