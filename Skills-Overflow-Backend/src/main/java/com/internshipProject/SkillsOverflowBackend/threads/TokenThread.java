package com.internshipProject.SkillsOverflowBackend.threads;


import com.internshipProject.SkillsOverflowBackend.repositories.TokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TokenThread implements Runnable{

//    @Autowired
//    MailService mailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void run() {
        while(true) {
            userRepository
                    .findAll()
                    .stream()
                    .filter(user ->
                            user.getEnabled()
                                    && LocalDateTime.now().isAfter(user.getVerificationToken().getExpirationDate()))
                    .forEach(user-> {
                        tokenRepository.delete(user.getVerificationToken());
                        userRepository.delete(user);
                    });
            try {
                Thread.sleep(1000*3600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
