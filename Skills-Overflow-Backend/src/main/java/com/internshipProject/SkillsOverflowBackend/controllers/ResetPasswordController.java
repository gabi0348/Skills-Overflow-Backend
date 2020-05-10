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

    @GetMapping("/changePassword")
    public void changePassword(HttpServletResponse httpServletResponse) {

        httpServletResponse.setHeader("Location", "127.0.0.1:5500/index.html");
        httpServletResponse.setStatus(302);
    }

    @PatchMapping("/savePassword/{id}")
    @ResponseBody
    public User savePassword(@PathVariable Long id, @RequestBody User user) {

        
        if (user.getResetPasswordToken() == null) {
            System.out.println("no token available");
            return null;
        }
        if (LocalDateTime.now().isAfter(user.getResetPasswordToken().getExpirationDate())) {
            System.out.println("expired time");
           return null;
       }
           userService.resetPassword(id, user);
        System.out.println(user.getPassword());
            return user;
    }


}
