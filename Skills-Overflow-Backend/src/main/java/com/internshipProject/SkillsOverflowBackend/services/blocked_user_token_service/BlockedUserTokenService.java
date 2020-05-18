package com.internshipProject.SkillsOverflowBackend.services.blocked_user_token_service;

import com.internshipProject.SkillsOverflowBackend.models.BlockedUserToken;
import com.internshipProject.SkillsOverflowBackend.models.User;

public interface BlockedUserTokenService {

    void createBlockedUserToken (User user, String token);
}
