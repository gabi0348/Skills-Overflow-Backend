package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.blocked_user_token_service.BlockedUserTokenService;
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

    @Autowired
    private BlockedUserTokenService blockedUserTokenService;

    public void confirmRegistrationMail(User user) {
        String token = UUID.randomUUID().toString();
        verificationTokenService.createVerificationTokenForUser(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl = "/registrationConfirm?token=" + token;
        String message = "Your account has been approved by the administrator. Click the link below to enable your account";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8081" + confirmationUrl);
        mailSender.send(email);
    }

    public void declineUserEmail(User user) {
        String recipientAddress = user.getEmail();
        String subject = "Declined registration";
        String message = "Your registration request has been denied";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }

    public void blockedUserEmail(User user){
        String token = UUID.randomUUID().toString();
        blockedUserTokenService.createBlockedUserToken(user, token);
        String recipientAddress = user.getEmail();
        String subject = "Blocked Account";
        String message = "Because of your recent activity your account has been blocked";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }

    public void promoteUserToAdminEmail(User user){

        String recipientAddress = user.getEmail();
        String subject = "You have been promoted to admin. Congratulations";
        String message = "We are pleased to inform you that you have just been promote to admin";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }

    public String resetPasswordMail(User user){
        String token = UUID.randomUUID().toString();
        resetPasswordTokenService.createPasswordTokenForUser(user, token);
        String recipientAddress = user.getEmail();
        String subject = "Reset password";
        String changePasswordUrl = "/changePassword/" + token;
        String message = "Click on this link to reset your password";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);

        //aici va trebuie sa intre pe un link din front-end;
        email.setText(message + "\r\n" + "http://localhost:3000" + changePasswordUrl);
        mailSender.send(email);

        return "mail sent";


    }

}
