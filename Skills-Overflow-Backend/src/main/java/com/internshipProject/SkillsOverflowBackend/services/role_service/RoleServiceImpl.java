package com.internshipProject.SkillsOverflowBackend.services.role_service;

import com.internshipProject.SkillsOverflowBackend.models.Role;
import com.internshipProject.SkillsOverflowBackend.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public Role getRoleById(Long id) {
        Role role = roleRepository.getOne(id);
        return role;
    }
}
