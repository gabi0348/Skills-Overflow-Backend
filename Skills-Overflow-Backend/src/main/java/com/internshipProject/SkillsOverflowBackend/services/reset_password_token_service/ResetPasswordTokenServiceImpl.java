package com.internshipProject.SkillsOverflowBackend.services.reset_password_token_service;

import com.internshipProject.SkillsOverflowBackend.models.ResetPasswordToken;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.ResetPasswordTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordTokenServiceImpl implements ResetPasswordTokenService {

    @Autowired
    ResetPasswordTokenRepository resetPasswordTokenRepository;

    public ResetPasswordToken getPasswordToken(String resetPasswordToken) {
        return resetPasswordTokenRepository.findByToken(resetPasswordToken);
    }

    public void createPasswordTokenForUser(User user, String token) {
        ResetPasswordToken resetPasswordToken = new ResetPasswordToken(token);
        resetPasswordToken.setUser(user);
        resetPasswordTokenRepository.saveAndFlush(resetPasswordToken);
    }
}
