package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.dto.ResetPasswordDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserProfilePostDTO;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.user_profile_service.UserProfileService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/userProfile")
public class UserProfileController {


    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserService userService;

    @PutMapping("/resetPasswordInUserProfile")
    @ResponseBody
    public String resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        return  userProfileService.resetPassword(resetPasswordDTO); 
    }

    @PutMapping("/changeUsername")
    @ResponseBody
    public String changeUsername(@RequestBody String username) {
        return  userProfileService.changeUsername(username);
    }

    @PutMapping("/changeName")
    @ResponseBody
    public String changeFirstAndLastName(@RequestBody User user) {
        return  userProfileService.changeFirstAndLastName(user);
    }

    @GetMapping("/getUserBySessionToken")
    @ResponseBody
    public UserDTO getUserDTObySessionToken() {
        return userService.findBySessionToken();
    }

    @GetMapping("/getUserPosts")
    @ResponseBody
    public List<UserProfilePostDTO> getUserPosts(){
        return userProfileService.getUserPosts();
    }

    @GetMapping("/getPostsWhereUserPostedComment")
    @ResponseBody
    public List<UserProfilePostDTO> getPostsWhereUserPostedComment(){
        return userProfileService.getPostsWhereUserPostedComment();
    }

}
