package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Role;
import com.sitblueprint.admin.repository.users.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        roleRepository.deleteById(roleId);
    }
}
