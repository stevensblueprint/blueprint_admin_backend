package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.AuthMember;

public interface AuthApiService {
    AuthMember createAuthMember(AuthMember authMember);
    AuthMember updateAuthMember(AuthMember authMember);

    AuthMember deleteAuthMember(String username);

    AuthMember disableAuthMember(String username);

    AuthMember enableAuthMember(String username);

    AuthMember resetPasswordAuthMember(String username, String password);
}
