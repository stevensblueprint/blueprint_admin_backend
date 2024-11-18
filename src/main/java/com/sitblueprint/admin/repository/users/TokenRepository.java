package com.sitblueprint.admin.repository.users;

import com.sitblueprint.admin.model.users.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}
