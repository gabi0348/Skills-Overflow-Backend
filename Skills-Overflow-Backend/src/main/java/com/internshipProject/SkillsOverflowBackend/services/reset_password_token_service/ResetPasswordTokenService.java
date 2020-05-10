package com.internshipProject.SkillsOverflowBackend.services.reset_password_token_service;

import com.internshipProject.SkillsOverflowBackend.models.ResetPasswordToken;
import com.internshipProject.SkillsOverflowBackend.models.User;

public interface ResetPasswordTokenService {

    ResetPasswordToken getPasswordToken(String resetPasswordToken);
    void createPasswordTokenForUser(User user, String resetPasswordToken);
}
