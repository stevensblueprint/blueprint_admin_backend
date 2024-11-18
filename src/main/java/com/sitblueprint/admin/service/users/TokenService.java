package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Token;
import java.util.Optional;

public interface TokenService {
  void saveConfirmationToken(Token token);
  Token getToken(String token);
  int setConfirmedAt(String token);
}
