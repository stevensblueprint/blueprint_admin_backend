package com.sitblueprint.admin.repository.users;

import com.sitblueprint.admin.model.users.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByUsername(String username);
}
