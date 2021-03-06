package com.internshipProject.SkillsOverflowBackend.services.verification_token_service;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
import com.internshipProject.SkillsOverflowBackend.repositories.VerificationTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService{

    @Autowired
    VerificationTokenRepository verificationTokenRepository;


    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByToken(verificationToken);
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        VerificationToken myVerificationToken = new VerificationToken(token);
        myVerificationToken.setUser(user);
        //user.setVerificationToken(myToken);
        // token este parintele, care persista si copilul fiindca am pus cascadeType= All.
        // Altfel n-ar fi putut persista fiindca nu-i obiect Java standard
        verificationTokenRepository.saveAndFlush(myVerificationToken);
    }


}
