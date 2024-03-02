package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.AuthUser;

import java.util.List;

public interface AuthApiService {
    // Define Methods to interact with the external API service
    List<AuthUser> getAllAuthUsers();

    AuthUser getAuthUser(String username);

    AuthUser updateAuthUser(AuthUser authUser);

    AuthUser deleteAuthUser(String username);

    AuthUser disableAuthUser(String username);

    AuthUser enableAuthUser(String username);

    AuthUser resetPasswordAuthUser(String username);
}
