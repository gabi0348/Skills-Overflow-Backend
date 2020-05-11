package com.internshipProject.SkillsOverflowBackend.threads;


import com.internshipProject.SkillsOverflowBackend.repositories.VerificationTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
<<<<<<< HEAD:Skills-Overflow-Backend/src/main/java/com/internshipProject/SkillsOverflowBackend/threads/VerificationTokenThread.java
=======
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import lombok.Getter;
import lombok.Setter;
>>>>>>> 49ec901fefa1e87996d8947303a1e070c6e27ce5:Skills-Overflow-Backend/src/main/java/com/internshipProject/SkillsOverflowBackend/threads/TokenThread.java
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

<<<<<<< HEAD:Skills-Overflow-Backend/src/main/java/com/internshipProject/SkillsOverflowBackend/threads/VerificationTokenThread.java
public class VerificationTokenThread extends Thread{
=======

@Setter
public class TokenThread extends Thread{
>>>>>>> 49ec901fefa1e87996d8947303a1e070c6e27ce5:Skills-Overflow-Backend/src/main/java/com/internshipProject/SkillsOverflowBackend/threads/TokenThread.java

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    boolean flag = true;

    @Override
    public void run() {
<<<<<<< HEAD:Skills-Overflow-Backend/src/main/java/com/internshipProject/SkillsOverflowBackend/threads/VerificationTokenThread.java
        while(true) {
            System.out.println("Token Repository is--->" + verificationTokenRepository.findAll().toString());
            System.out.println("User Repository is--->" + userRepository.findAll().toString());
=======
        while(flag) {
            System.out.println("Token Repository is ---> " + tokenRepository.findAll().toString());
            System.out.println("User Repository is ---> " + userRepository.findAll().toString());

>>>>>>> 49ec901fefa1e87996d8947303a1e070c6e27ce5:Skills-Overflow-Backend/src/main/java/com/internshipProject/SkillsOverflowBackend/threads/TokenThread.java
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
