package com.internshipProject.SkillsOverflowBackend.services.user_profile_service;

import com.internshipProject.SkillsOverflowBackend.dto.ResetPasswordDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserProfilePostDTO;
import com.internshipProject.SkillsOverflowBackend.models.User;

import java.util.List;

public interface UserProfileService {

    String resetPassword(ResetPasswordDTO resetPasswordDTO);

    String changeUsername(String username);

    String changeFirstAndLastName(User user);

    List<UserProfilePostDTO> getUserPosts();

    List<UserProfilePostDTO> getPostsWhereUserPostedComment();
}
