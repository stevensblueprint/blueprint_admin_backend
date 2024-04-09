package com.sitblueprint.admin.repository.applications;

import com.sitblueprint.admin.model.applications.ApplicationSatisfactionForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationSatisfactionFormRepository  extends JpaRepository<ApplicationSatisfactionForm, Long> {
}
