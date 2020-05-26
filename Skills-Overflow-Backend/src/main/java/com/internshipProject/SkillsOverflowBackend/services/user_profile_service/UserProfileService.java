package com.internshipProject.SkillsOverflowBackend.services.user_profile_service;

import com.internshipProject.SkillsOverflowBackend.dto.ResetPasswordDTO;
import com.internshipProject.SkillsOverflowBackend.models.User;

public interface UserProfileService {

    String resetPassword(ResetPasswordDTO resetPasswordDTO);

    String changeUsername(User user);
    String changeFirstAndLastName(User user);
}
