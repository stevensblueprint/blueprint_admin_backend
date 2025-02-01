package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Token;

public interface TokenService {
	void saveConfirmationToken(Token token);
	Token getToken(String token);
	int setConfirmedAt(String token);
}
