package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.convertors.UserConverter;
import com.internshipProject.SkillsOverflowBackend.dto.UserDto;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    private List<UserDto> usersDto = new ArrayList<>();


    public void convertAllUsers(List<User> usersList){
        usersDto.clear();
        usersList = userRepository.findAll();
        for(User user : usersList){
            UserDto userDto = UserConverter.convertToUserDto(user);
                this.usersDto.add(userDto);
        }
    }

    @Override
    public List<UserDto> findAllDto() {
        usersDto.clear();
        List<User> usersList = userRepository.findAll();
        convertAllUsers(usersList);
        return this.usersDto;
    }

    @Override
    public  User addUser(User user) {
//        usersDto.clear();
        if (checkForExistingEmailOrUsername(user.getEmail(), user.getUserName())) {
            return null;
        }
        userRepository.saveAndFlush(user);
        return user;

    }

    @Override
    public UserDto getUserDtoById(Long id) {
        User user = userRepository.getOne(id);
        return UserConverter.convertToUserDto(user);
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
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
    public void saveRegisteredUser(User user) {
        userRepository.saveAndFlush(user);
    }


}
