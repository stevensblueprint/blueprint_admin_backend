package com.sitblueprint.admin.repository.users;

import com.sitblueprint.admin.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByTeamId(Long teamId);
}
