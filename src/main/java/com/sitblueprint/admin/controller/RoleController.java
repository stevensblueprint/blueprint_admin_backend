package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.Role;
import com.sitblueprint.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role/")
public class RoleController {
	RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}

	@GetMapping("/{id}")
	public Role getRoleById(@PathVariable String id) {
		return roleService.getRoleById(Long.parseLong(id));
	}

	@PostMapping
	public Role createRole(@RequestBody Role role) {
		return roleService.createRole(role);
	}

	@DeleteMapping
	public void deleteRole(String roleId) {
		roleService.deleteRole(Long.parseLong(roleId));
	}
}
