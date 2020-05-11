package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.reset_password_token_service.ResetPasswordTokenService;
import com.internshipProject.SkillsOverflowBackend.services.verification_token_service.VerificationTokenService;
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
    private VerificationTokenService verificationTokenService;

    @Autowired
    private ResetPasswordTokenService resetPasswordTokenService;

    public void confirmRegistrationMail(User user) {
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationTokenForUser(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "/registrationConfirm?token=" + token;
        String message = "You registered successfully. We will send you a confirmation message to your email account.";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }

    public void resetPasswordMail(User user){
        String token = UUID.randomUUID().toString();
        resetPasswordTokenService.createPasswordTokenForUser(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Reset password";
        String changePasswordUrl = "/changePassword?token=" + token;
        String message = "Click on this link to reset your password";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080" + changePasswordUrl);
        mailSender.send(email);


    }

}
