package com.sitblueprint.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "teams")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JsonIgnore
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