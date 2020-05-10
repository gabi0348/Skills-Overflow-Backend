package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
import com.internshipProject.SkillsOverflowBackend.services.TokenService;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestController
public class RegistrationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;


    @GetMapping("/registrationConfirm")
    public void confirmRegistration(@RequestParam("token") String token, HttpServletResponse httpServletResponse) {

        VerificationToken verificationToken = tokenService.getVerificationToken(token);

        if (verificationToken == null) {
            //return "no token available";
            return;
        }

        if (LocalDateTime.now().isAfter(verificationToken.getExpirationDate())) {
            //return "expired time";
            return;
        }

        User user = verificationToken.getUser();
        user.setEnabled(true);
        userService.saveRegisteredUser(user);

        //redirect pe alta pagina din front-end, dupa sign-up.
        // https://stackoverflow.com/questions/57395668/how-to-redirect-from-spring-controller-to-react-page
        httpServletResponse.setHeader("Location", "http://localhost:8081/users");
        httpServletResponse.setStatus(302);

    }
}
