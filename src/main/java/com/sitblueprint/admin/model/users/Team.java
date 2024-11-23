package com.sitblueprint.admin.model.users;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.LocalDate;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "organization_id", nullable = false)
	private Organization organization;

	private String name;

	@ManyToOne
	@JoinColumn(name = "team_lead_id")
	private Member teamLead;

	@ManyToOne
	@JoinColumn(name = "project_manager_id")
	private Member projectManager;

	@ManyToOne
	@JoinColumn(name = "designer_id")
	private Member designer;

	@Column(nullable = false)
	private LocalDate dateCreated;

	@OneToMany(mappedBy = "team")
	private Set<Member> members;
}