package com.sitblueprint.admin.model.npos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sitblueprint.admin.model.users.Team;

import java.time.LocalDate;

@Entity
@Table(name = "npos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NPO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team teamAssigned;

	@Column(nullable = false)
	private String projectProposalUrl;

	@Column(nullable = false)
	private LocalDate dateOfRecruitment;
}
