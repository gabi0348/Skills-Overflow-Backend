package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.User;

import java.util.List;

public interface UserService {

    List<User> list();

    User addUser(User user);

    User getUserById(Long id);

    void removeUserById(Long id);

    boolean checkForExistingEmailOrUsername(String email, String username);

    boolean validateEmailAndPassword(String email, String password);

    User updateUser(Long id, User user);

    void saveRegisteredUser(User user);

}
