package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.dto.LoginDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserDTO;
import com.internshipProject.SkillsOverflowBackend.models.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAllDto();

    //??
    String addUser(User user);

    UserDTO getUserDtoById(Long id);

    void removeUserById(Long id);

    boolean checkForExistingEmailOrUsername(String email, String username);

    void saveRegisteredUser(User user);

    String userExists(LoginDTO loginDTO);



}
