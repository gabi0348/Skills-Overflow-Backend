package com.internshipProject.SkillsOverflowBackend.threads;

import com.internshipProject.SkillsOverflowBackend.models.BlockedUserToken;
import com.internshipProject.SkillsOverflowBackend.models.Role;
import com.internshipProject.SkillsOverflowBackend.repositories.BlockedUserTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.ResetPasswordTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

    public class BlockedUserTokenThread extends Thread{

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserService userService;

        @Autowired
        private BlockedUserTokenRepository blockedUserTokenRepository;

        @Override
        public void run() {
            while(true) {
                if (userRepository.findAll().size()!=0 && blockedUserTokenRepository.findAll().size()!=0) {
                    userRepository
                            .findAll()
                            .forEach(user -> {
                                if(user.getBlockedUserToken() != null && LocalDateTime.now().isAfter(user.getBlockedUserToken().getExpirationDate())) {
                                    Role role = new Role(2L, "approved user");
                                    user.setRole(role);
                                    blockedUserTokenRepository.delete(user.getBlockedUserToken());
                                }

                            });
                }
                try {
                    Thread.sleep(1000*20);
                    System.out.println("NOT SLEEPING ANYMORE");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

