package com.internshipProject.SkillsOverflowBackend.convertors;

import com.internshipProject.SkillsOverflowBackend.dto.UserProfilePostDTO;
import com.internshipProject.SkillsOverflowBackend.models.Post;

public class UserProfilePostConverter {

    public static UserProfilePostDTO convertToUserProfilePostDTO(Post post) {
        UserProfilePostDTO userProfilePostDTO = new UserProfilePostDTO();
        userProfilePostDTO.setPostId(post.getId());
        userProfilePostDTO.setTitle(post.getTitle());
        return userProfilePostDTO;
    }
}
