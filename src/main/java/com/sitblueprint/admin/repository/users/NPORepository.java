package com.sitblueprint.admin.repository.users;

import com.sitblueprint.admin.model.npos.NPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface NPORepository extends JpaRepository<NPO, Long> {
}
