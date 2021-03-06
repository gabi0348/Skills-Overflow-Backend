package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.models.ResetPasswordToken;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import com.internshipProject.SkillsOverflowBackend.services.reset_password_token_service.ResetPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin
public class ResetPasswordController {

    @Autowired
    private ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("email") String email) {
        return userService.findByEmailAndSendResetPasswordEmail(email);
    }

    @PutMapping("/savePassword")
    @ResponseBody
    public String savePassword(@RequestParam("token") String token, @RequestBody User user) {

        ResetPasswordToken resetPasswordToken = resetPasswordTokenService.getPasswordToken(token);

        if (resetPasswordToken == null) {
            return "no token available";
        }
        if (LocalDateTime.now().isAfter(resetPasswordToken.getExpirationDate())) {
           return "expired time";
       }

        userService.resetPassword(token, user);
        User foundUser = resetPasswordToken.getUser();
        foundUser.setChangedPassword(true);
        userService.saveUser(foundUser);


        return "password successfully changed";
    }


}
