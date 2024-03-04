package com.sitblueprint.admin.model.applications;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "application_satisfaction_forms")
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationSatisfactionForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @OneToOne(mappedBy = "satisfactionForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private ApplicationForm applicationForm;

    private int rating;
    private String body;
}
