package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.models.ResetPasswordToken;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import com.internshipProject.SkillsOverflowBackend.services.reset_password_token_service.ResetPasswordTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
public class ResetPasswordController {

    @Autowired
    private ResetPasswordTokenService resetPasswordTokenService;

    @Autowired
    private UserService userService;

    @PostMapping("/resetPassword")
    public User resetPassword(@RequestParam("email") String email) {
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

        return "password successfully changed";
    }


}
