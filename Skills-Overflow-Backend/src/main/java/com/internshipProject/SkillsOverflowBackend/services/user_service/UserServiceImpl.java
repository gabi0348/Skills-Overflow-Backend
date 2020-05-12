package com.internshipProject.SkillsOverflowBackend.services.user_service;

import com.internshipProject.SkillsOverflowBackend.convertors.UserConverter;

import com.internshipProject.SkillsOverflowBackend.dto.LoginDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserDTO;
import com.internshipProject.SkillsOverflowBackend.models.Role;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.ResetPasswordTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;


    private List<UserDTO> usersDto = new ArrayList<>();
    private Set<Role> userRoles = new HashSet<>();


    public void convertAllUsers(List<User> usersList) {
        usersDto.clear();
        usersList = userRepository.findAll();
        for (User user : usersList) {
            UserDTO userDto = UserConverter.convertToUserDto(user);
            this.usersDto.add(userDto);
        }
    }

    @Override
    public List<UserDTO> findAllDto() {
        usersDto.clear();
        List<User> usersList = userRepository.findAll();
        convertAllUsers(usersList);
        return this.usersDto;
    }

    @Override
    public String addUser(User user) {
        userRoles.clear();
        if(checkForExistingEmail(user.getEmail())){
            return "email already taken";
        } else if(checkForExistingUsername(user.getUserName())){
            return "username already taken";
        }

        userRoles.add(new Role(1L, "user"));
        user.setRoles(userRoles);
        mailService.confirmRegistrationMail(user);
        //nou Thread, ca front-end-ul să nu mai aștepte după back-end
        new Thread(() -> mailService.confirmRegistrationMail(user)).start();
        return "Please check your email";
    }

    public User findByEmailAndSendResetPasswordEmail(String email){
       User user = userRepository.findByEmail(email);
       mailService.resetPasswordMail(user);
       return user;
    }

    @Override
    public String userExists(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());

        if (user != null && user.getPassword().equals(loginDTO.getPassword()))
            return "user exists";
        else
            return "user does not exist";
    }

    @Override
    public UserDTO getUserDtoById(Long id) {
        User user = userRepository.getOne(id);
        UserDTO userDto = UserConverter.convertToUserDto(user);
        return userDto;
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkForExistingEmail(String email){
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkForExistingUsername(String username) {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User resetPassword(String token, User user) {
        User existingUser = resetPasswordTokenRepository.findByToken(token).getUser() ;
        existingUser.setPassword(user.getPassword());
        userRepository.saveAndFlush(existingUser);
        return existingUser;
    }


}
