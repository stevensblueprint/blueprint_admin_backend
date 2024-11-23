package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Token;
import com.sitblueprint.admin.repository.users.TokenRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
	private final TokenRepository tokenRepository;

	@Autowired
	public TokenServiceImpl(TokenRepository tokenRepository) {
		this.tokenRepository = tokenRepository;
	}

	@Override
	public void saveConfirmationToken(Token token) {
		tokenRepository.save(token);
	}

	@Override
	public Token getToken(String token) {
		return tokenRepository.findByToken(token);
	}

	@Override
	public int setConfirmedAt(String token) {
		return tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
	}
}
