package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
import com.internshipProject.SkillsOverflowBackend.services.TokenService;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class RegistrationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;


    @GetMapping("/registrationConfirm")
    public String confirmRegistration(@RequestParam("token") String token) {

        VerificationToken verificationToken = tokenService.getVerificationToken(token);
        System.out.println("the tokein is" + token);
        System.out.println("the verification toke ins " + verificationToken);
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
