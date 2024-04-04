package com.sitblueprint.admin.service.applications;

import com.sitblueprint.admin.model.applications.ApplicationForm;

import java.util.List;

public interface ApplicationFormService {
    List<ApplicationForm> getApplicationForms();

    ApplicationForm getApplicationFormById(Long applicationId);

    ApplicationForm createApplicationForm(ApplicationForm application);

    ApplicationForm updateApplicationForm(ApplicationForm application);

    void deleteApplicationFormById(Long applicationId);
}
