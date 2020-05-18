package com.internshipProject.SkillsOverflowBackend.services.role_service;

import com.internshipProject.SkillsOverflowBackend.models.Role;
import com.internshipProject.SkillsOverflowBackend.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByRoleName(String userRole) {
        Role role = roleRepository.findByRole(userRole);
        return role;
    }


}
