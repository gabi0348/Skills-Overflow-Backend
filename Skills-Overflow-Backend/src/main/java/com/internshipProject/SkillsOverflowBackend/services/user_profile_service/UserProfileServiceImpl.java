package com.internshipProject.SkillsOverflowBackend.services.user_profile_service;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.dto.ResetPasswordDTO;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public String resetPassword(ResetPasswordDTO resetPasswordDTO){
        User existingUser = userService.findByEmail(jwtTokenProvider.getUser().getEmail());
        if(existingUser.getPassword().equals(resetPasswordDTO.getOldPassword())){
            existingUser.setPassword(resetPasswordDTO.getNewPassword());
        } else {
            return "old password doesn't match the input";
        }
        userService.saveUser(existingUser);
        return "password successfully changed";
    }

    @Override
    public String changeUsername(String username){
        User existingUser = userService.findByEmail(jwtTokenProvider.getUser().getEmail());
        existingUser.setUserName(username);
        userService.saveUser(existingUser);
        return "username successfully changed";
    }

    @Override
    public String changeFirstAndLastName(User user){
        User existingUser = userService.findByEmail(jwtTokenProvider.getUser().getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        userService.saveUser(existingUser);
        return "first name and last name successfully changed";
    }


}
