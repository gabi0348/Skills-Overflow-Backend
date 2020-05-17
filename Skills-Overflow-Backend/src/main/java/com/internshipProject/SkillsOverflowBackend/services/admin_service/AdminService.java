package com.internshipProject.SkillsOverflowBackend.services.admin_service;

import com.internshipProject.SkillsOverflowBackend.models.User;

public interface AdminService {

    String approveRequest(Long id, User user);
    String blockUser(Long id, User user);
    String unblockUser(Long id, User user);
    String declineRequest(Long id, User user);
    String promoteUserToAdmin(Long id, User user);
}
