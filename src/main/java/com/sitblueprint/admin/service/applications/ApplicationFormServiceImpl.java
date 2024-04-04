package com.sitblueprint.admin.service.applications;

import com.sitblueprint.admin.model.applications.ApplicationForm;
import com.sitblueprint.admin.repository.applications.ApplicationFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationFormServiceImpl implements ApplicationFormService {
    @Autowired
    ApplicationFormRepository applicationFormRepository;

    @Override
    public List<ApplicationForm> getApplicationForms() {
        return applicationFormRepository.findAll();
    }

    @Override
    public ApplicationForm getApplicationFormById(Long applicationId) {
        return applicationFormRepository.findById(applicationId).get();
    }

    @Override
    public ApplicationForm createApplicationForm(ApplicationForm application) {
        return applicationFormRepository.save(application);
    }

    @Override
    public ApplicationForm updateApplicationForm(ApplicationForm application) {
        return applicationFormRepository.saveAndFlush(application);
    }

    @Override
    public void deleteApplicationFormById(Long applicationId) {
        applicationFormRepository.deleteById(applicationId);
    }
}
