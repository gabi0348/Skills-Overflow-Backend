package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
import com.internshipProject.SkillsOverflowBackend.repositories.TokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;

    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken myToken = new VerificationToken(token);
        myToken.setUser(user);

        // token este parintele, care va persista si copilul (user) mai intai, fiindca am pus cascadeType = PERSIST.
        // Altfel n-ar fi putut persista, și ar fi dat eroarea - save transient object first;
        tokenRepository.saveAndFlush(myToken);
    }

}
