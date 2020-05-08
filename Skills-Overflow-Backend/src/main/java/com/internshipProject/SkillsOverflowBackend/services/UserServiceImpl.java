package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
import com.internshipProject.SkillsOverflowBackend.repositories.TokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public List<User> list() {
        List<User> all = userRepository.findAll();
        return all;
    }

    @Override
    public  User addUser(User user) {
        if(checkForExistingEmailOrUsername(user.getEmail(), user.getUserName())){
            return null;
        } else if (!validateEmailAndPassword(user.getEmail(), user.getPassword()))
        userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUser(Long id, User user){
        User existingUser = userRepository.getOne(id);
        existingUser.setUserName(user.getUserName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        return userRepository.saveAndFlush(existingUser);
    }

    @Override
    public boolean checkForExistingEmailOrUsername(String email, String username){
        List<User> allUsers = userRepository.findAll();
        for(User user : allUsers) {
            if(user.getEmail().equals(email)){
                return true;
            } else if (user.getUserName().equals(username)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validateEmailAndPassword(String email, String password){
        if (email.equals("") || password.equals("")) {
            return false;
        }

        int atIndex = email.indexOf("@");

        if (email.lastIndexOf("@") != atIndex) {
            return false;
        }

        String beforeAt = email.substring(0, atIndex);

        if (!beforeAt.contains(".")) {
            return false;
        }

        if (!beforeAt.matches("[a-zA-Z]+" + "." + "[a-zA-Z]+")) {
            return false;
        }

        return true;

    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.saveAndFlush(user);
    }

}
