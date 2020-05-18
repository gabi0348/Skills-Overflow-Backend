package com.internshipProject.SkillsOverflowBackend.services.user_service;

import com.internshipProject.SkillsOverflowBackend.dto.LoginDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserDTO;
import com.internshipProject.SkillsOverflowBackend.models.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAllDto();

    String addUser(User user);

    String findByEmailAndSendResetPasswordEmail(String email);

    UserDTO getUserDtoById(Long id);

    void removeUserById(Long id);

    void saveUser(User user);

    User resetPassword(String token, User user);

    String userExists(LoginDTO loginDTO);

    boolean checkForExistingEmail(String email);

    boolean checkForExistingUsername(String username);

    void convertAllUsers(List<User> usersList);

    List<UserDTO> findAllUsersByRole(String userRole);

    User findById(Long id);

    User findByUserName(String userName);

}
