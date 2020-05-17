package com.internshipProject.SkillsOverflowBackend.threads;

import com.internshipProject.SkillsOverflowBackend.enums.UsersRoles;
import com.internshipProject.SkillsOverflowBackend.models.Role;
import com.internshipProject.SkillsOverflowBackend.repositories.BlockedUserTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
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

        @Autowired
        private MailService mailService;

        @Override
        public void run() {
            while(true) {
                if (userRepository.findAll().size()!=0 && blockedUserTokenRepository.findAll().size()!=0) {
                    userRepository
                            .findAll()
                            .forEach(user -> {
                                if(user.getBlockedUserToken() != null && LocalDateTime.now().isAfter(user.getBlockedUserToken().getExpirationDate())) {
                                    if(user.getBlockCount() < 2) {
                                        Role role = new Role(2L, UsersRoles.APPROVED_USER.toString());
                                        user.setRole(role);
                                        user.setBlockCount(user.getBlockCount()+1L);
                                        blockedUserTokenRepository.delete(user.getBlockedUserToken());
                                    } else if(user.getBlockCount() == 2){
                                        user.setBlockCount(0L);
                                        blockedUserTokenRepository.delete(user.getBlockedUserToken());
                                        mailService.blockedUserIndefinitelyEmail(user);
                                    }
                                }
                                if(user.getBlockedUserToken() != null && user.getRole().getRole().equals(UsersRoles.APPROVED_USER.toString())){
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

