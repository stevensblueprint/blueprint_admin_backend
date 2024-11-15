package com.sitblueprint.admin.model.users;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "teams")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonBackReference(value = "organization-teams")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    private String name;

    @JsonIgnoreProperties({"team", "roles"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_lead_id")
    private Member teamLead;

    @JsonIgnoreProperties({"team", "roles"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_manager_id")
    private Member projectManager;

    @JsonIgnoreProperties({"team", "roles"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "designer_id")
    private Member designer;

    @JsonManagedReference(value = "team-members")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private Set<Member> members = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime dateCreated;
}