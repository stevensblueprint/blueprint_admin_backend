package com.sitblueprint.admin.model.users;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
@Entity
@Table(name = "organizations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_lead_id")
    private Member teamLead;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_manager_id")
    private Member projectManager;

    @JsonManagedReference(value = "organization-teams")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organization")
    private Set<Team> teams = new HashSet<>();
}
