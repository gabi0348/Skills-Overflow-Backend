package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.dto.ResetPasswordDTO;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.user_profile_service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/userProfile")
public class UserProfileController {


    @Autowired
    private UserProfileService userProfileService;

    @PutMapping("/resetPasswordInUserProfile")
    @ResponseBody
    public String resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        return  userProfileService.resetPassword(resetPasswordDTO);
    }

    @PutMapping("/changeUsername")
    @ResponseBody
    public String changeUsername(@RequestBody User user) {
        return  userProfileService.changeUsername(user);
    }

    @PutMapping("/changeName")
    @ResponseBody
    public String changeFirstAndLastName(@RequestBody User user) {
        return  userProfileService.changeFirstAndLastName(user);
    }

}
