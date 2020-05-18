package com.internshipProject.SkillsOverflowBackend.services.admin_service;

import com.internshipProject.SkillsOverflowBackend.models.User;

public interface AdminService {

    String approveRequest(Long id);
    String blockUser(Long id);
    String unblockUser(Long id);
    String declineRequest(Long id);
    String promoteUserToAdmin(Long id);
}
