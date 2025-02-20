package com.sitblueprint.admin.repository;

<<<<<<<< HEAD:src/main/java/com/sitblueprint/admin/repository/MemberRepository.java
import com.sitblueprint.admin.model.Member;
========
import com.sitblueprint.admin.model.Organization;
>>>>>>>> fix-issue-42:src/main/java/com/sitblueprint/admin/repository/OrganizationRepository.java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
<<<<<<<< HEAD:src/main/java/com/sitblueprint/admin/repository/MemberRepository.java
public interface MemberRepository extends JpaRepository<Member, Long> {
	Member findByUsername(String username);
========
public interface OrganizationRepository extends JpaRepository<Organization, Long> {
>>>>>>>> fix-issue-42:src/main/java/com/sitblueprint/admin/repository/OrganizationRepository.java
}
