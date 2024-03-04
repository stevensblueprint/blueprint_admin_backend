package com.sitblueprint.admin.model.applications;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "application_forms")
public class ApplicationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String graduationYear;
    private String major;
    private int timesAppliedPreviously;
    private String role;
    private boolean isAccepted;

    @OneToOne
    @JoinColumn(name = "satisfaction_application_id")
    private ApplicationSatisfactionForm satisfactionForm;
}
