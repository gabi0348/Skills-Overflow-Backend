package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.dto.UserDto;
import com.internshipProject.SkillsOverflowBackend.models.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<UserDto> findAllDto();

    //??
    String addUser(User user);

    UserDto getUserDtoById(Long id);

    void removeUserById(Long id);

    boolean checkForExistingEmailOrUsername(String email, String username);

    void saveRegisteredUser(User user);



}
