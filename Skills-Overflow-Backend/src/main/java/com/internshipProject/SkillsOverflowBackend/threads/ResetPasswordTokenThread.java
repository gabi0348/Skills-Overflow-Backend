package com.internshipProject.SkillsOverflowBackend.threads;

import com.internshipProject.SkillsOverflowBackend.repositories.ResetPasswordTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class ResetPasswordTokenThread extends Thread{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Override
    public void run() {
        while(true) {
            if (userRepository.findAll().size()!=0 && resetPasswordTokenRepository.findAll().size()!=0) {
                userRepository
                        .findAll()
                        .forEach(user -> {
                            if(user.getChangedPassword()){
                                resetPasswordTokenRepository.delete(user.getResetPasswordToken());
                                user.setChangedPassword(false);
                                userService.saveUser(user);
                            }
                            if (user.getResetPasswordToken()!= null && LocalDateTime.now().isAfter(user.getResetPasswordToken()
                                    .getExpirationDate())) {
                                    resetPasswordTokenRepository.delete(user.getResetPasswordToken());
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
