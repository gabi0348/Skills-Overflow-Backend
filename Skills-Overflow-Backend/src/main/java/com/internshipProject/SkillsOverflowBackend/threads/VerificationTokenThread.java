package com.internshipProject.SkillsOverflowBackend.threads;


import com.internshipProject.SkillsOverflowBackend.repositories.VerificationTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class VerificationTokenThread extends Thread{

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public void run() {
        while(true) {
            System.out.println("Token Repository is--->" + verificationTokenRepository.findAll().toString());
            System.out.println("User Repository is--->" + userRepository.findAll().toString());
            //am scris acest if pentru setupul initial cand userRepository si tokenRepository sunt goale, ca sa nu arunce NullPointer
            if (userRepository.findAll().size()!=0 && verificationTokenRepository.findAll().size()!=0) {
                userRepository
                        .findAll()
                        .forEach(user -> {
                            if (user.getVerificationToken()!= null && LocalDateTime.now().isAfter(user.getVerificationToken()
                                    .getExpirationDate())) {
                                verificationTokenRepository.delete(user.getVerificationToken());
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
