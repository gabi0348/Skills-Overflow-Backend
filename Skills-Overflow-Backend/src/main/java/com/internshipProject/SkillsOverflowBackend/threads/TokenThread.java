package com.internshipProject.SkillsOverflowBackend.threads;


import com.internshipProject.SkillsOverflowBackend.repositories.TokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class TokenThread implements Runnable{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void run() {
        while(true) {
            if (userRepository.findAll().size()!=0 && tokenRepository.findAll().size()!=0) {
                userRepository
                        .findAll()
                        .forEach(user -> {
                            if (LocalDateTime.now().isAfter(user.getVerificationToken()
                                    .getExpirationDate())) {
                                tokenRepository.delete(user.getVerificationToken());
                                System.out.println("Token is..." + user.getVerificationToken());
                                System.out.println("-----------");
                                if (!user.getEnabled()) {
                                    userRepository.delete(user);
                                    System.out.println("User is ..." + user);
                                }
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
