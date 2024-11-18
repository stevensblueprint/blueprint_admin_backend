package com.sitblueprint.admin.model.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name; // E-BOARD, TEAM_LEAD, PRODUCT_MANAGER, DEVELOPER, BLUEPRINT_INTERNAL_TEAM
}
