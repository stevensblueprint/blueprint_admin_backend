package com.sitblueprint.admin.model;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
=======
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
>>>>>>> fix-issue-42

@Entity
@Table(name = "organizations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "team_lead_id")
	private Member teamLead;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project_manager_id")
	private Member projectManager;

	@OneToMany(mappedBy = "organization")
	private Set<Team> teams;
=======
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = true)
    @JoinColumn(name = "team_lead_id")
    private Member teamLead;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_manager_id")
    private Member projectManager;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Team> teams = new HashSet<>(); 
>>>>>>> fix-issue-42
}
