package com.internshipProject.SkillsOverflowBackend.threads;


import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
import com.internshipProject.SkillsOverflowBackend.repositories.TokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;


@Setter
public class TokenThread extends Thread{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    boolean flag = true;

    @Override
    public void run() {
        while(flag) {
            System.out.println("Token Repository is ---> " + tokenRepository.findAll().toString());
            System.out.println("User Repository is ---> " + userRepository.findAll().toString());

            //am scris acest if pentru setupul initial cand userRepository si tokenRepository sunt goale, ca sa nu arunce NullPointer
            if (userRepository.findAll().size()!=0 && tokenRepository.findAll().size()!=0) {
                userRepository
                        .findAll()
                        .forEach(user -> {
                            if (user.getVerificationToken()!= null && LocalDateTime.now().isAfter(user.getVerificationToken()
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
