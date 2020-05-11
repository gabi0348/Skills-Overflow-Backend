package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
import com.internshipProject.SkillsOverflowBackend.services.VerificationTokenService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class VerificationController {

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private UserService userService;


    @GetMapping("/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token) {

        VerificationToken verificationToken = verificationTokenService.getVerificationToken(token);

        if (verificationToken == null) {
            return "no token available";
        }

        if (LocalDateTime.now().isAfter(verificationToken.getExpirationDate())) {
            return "expired time";
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return "successful register";
    }
}
