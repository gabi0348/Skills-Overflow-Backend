package com.internshipProject.SkillsOverflowBackend.services.verification_token_service;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;

public interface    VerificationTokenService {

    VerificationToken getVerificationToken(String verificationToken);
    void createVerificationTokenForUser(User user, String token);
}
