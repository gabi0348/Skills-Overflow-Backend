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
        myToken.setUser(user); //o să crape aici: save transient value first; pot doar să salvez token-ul fără user, dar voi avea nevoie ca tokenul să refere userul...
        //dacă adaug pe child/tokenrepository cascadetype=all, tot nu merge - a different object was declared with the same identifier: User2

        tokenRepository.saveAndFlush(myToken);

        //myToken.setUser(user);


    }

}
