package com.sitblueprint.admin.repository.applications;

import com.sitblueprint.admin.model.applications.ApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationFormRepository extends JpaRepository<ApplicationForm, Long> {
    List<ApplicationForm> findApplicationFormByRole(String role);

    List<ApplicationForm> findApplicationFormByGraduationYear(String graduationYear);
}
