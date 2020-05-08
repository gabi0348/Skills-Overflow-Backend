package com.internshipProject.SkillsOverflowBackend.threads;


import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
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
            System.out.println("Token Repository is--->" + tokenRepository.findAll().toString());
            System.out.println("User Repository is--->" + userRepository.findAll().toString());
            //am scris acest if pentru setupul initial cand userRepository si tokenRepository sunt goale, ca sa nu arunce NullPointer
            if (userRepository.findAll().size()!=0 && tokenRepository.findAll().size()!=0) {
                userRepository
                        .findAll()
                        .forEach(user -> {
                            if (LocalDateTime.now().isAfter(user.getVerificationToken()
                                    .getExpirationDate())) {
                                tokenRepository.delete(user.getVerificationToken());
                                if (!user.getEnabled()) {
                                    userRepository.delete(user);
                                }
                            }
                        });
            }
            try {
                Thread.sleep(1000*20);
                System.out.println("NOT SLEEPING ANYMORE");
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
