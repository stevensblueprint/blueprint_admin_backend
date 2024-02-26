package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleById(Long roleId);

    Role createRole(Role role);

    void deleteRole(Long roleId);
}
