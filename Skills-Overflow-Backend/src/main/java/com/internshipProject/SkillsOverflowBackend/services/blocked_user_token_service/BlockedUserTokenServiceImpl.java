package com.internshipProject.SkillsOverflowBackend.services.blocked_user_token_service;

import com.internshipProject.SkillsOverflowBackend.models.BlockedUserToken;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.BlockedUserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlockedUserTokenServiceImpl implements BlockedUserTokenService{

    @Autowired
    private BlockedUserTokenRepository blockedUserTokenRepository;


    @Override
    public void createBlockedUserToken (User user, String token) {
        BlockedUserToken blockedUserToken = new BlockedUserToken(token);
        blockedUserToken.setUser(user);
        blockedUserTokenRepository.saveAndFlush(blockedUserToken);
    }

}
