package com.sitblueprint.admin.repository.users;

import com.sitblueprint.admin.model.users.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
