package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TokenService tokenService;

    public void confirmRegistration(User user) {
        String token = UUID.randomUUID().toString();
        tokenService.createVerificationTokenForUser(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "/registrationConfirm?token=" + token;
        String message = "You registered successfully. We will send you a confirmation message to your email account.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8081" + confirmationUrl);
        mailSender.send(email);
    }

    public void resetPassword(User user){

    }

}
