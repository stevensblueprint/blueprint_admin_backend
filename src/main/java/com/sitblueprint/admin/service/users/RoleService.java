package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    Role getRoleById(Long roleId);

    Role createRole(Role role);

    void deleteRole(Long roleId);
}
