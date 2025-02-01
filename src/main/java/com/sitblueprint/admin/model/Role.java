package com.sitblueprint.admin.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Set;
import lombok.*;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name; // E-BOARD, TEAM_LEAD, PRODUCT_MANAGER, DEVELOPER, BLUEPRINT_INTERNAL_TEAM

	@ManyToMany(mappedBy = "roles")
	private Set<Member> members;

	public static Role of(String name) {
		return Role.builder().name(name).build();
	}
}
