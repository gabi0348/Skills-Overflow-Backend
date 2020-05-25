package com.internshipProject.SkillsOverflowBackend.services.admin_service;

public interface AdminService {

    String approveRequest(Long id);
    String blockUser(Long id);
    String unblockUser(Long id);
    String declineRequest(Long id);
    String promoteUserToAdmin(Long id);
    String approvePost(Long id);
    String approveComment(Long id);
    String deletePost(Long id);
    String deleteComment(Long id);
}
