package com.sitblueprint.admin.controller.users;

import com.sitblueprint.admin.model.users.Role;
import com.sitblueprint.admin.service.users.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role/")
public class RoleController {

	@Autowired
	RoleService roleService;

	@GetMapping("all")
	public List<Role> getAllRoles() {
		return roleService.getAllRoles();
	}

	@GetMapping
	public Role getRoleById(@Param("roleId") String roleId) {
		return roleService.getRoleById(Long.parseLong(roleId));
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
