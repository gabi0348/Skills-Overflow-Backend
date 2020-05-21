package com.internshipProject.SkillsOverflowBackend.convertors;

import com.internshipProject.SkillsOverflowBackend.dto.NotificationDTO;
import com.internshipProject.SkillsOverflowBackend.dto.PostDTO;
import com.internshipProject.SkillsOverflowBackend.models.Notification;
import com.internshipProject.SkillsOverflowBackend.models.Post;

public class PostConverter {

    public static PostDTO convertToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setBody(post.getBody());
        postDTO.setCreateDate(post.getCreateDate().toLocalDate().toString());
        postDTO.setNumberOfComments(post.getNumberOfComments());
        postDTO.setTitle(post.getTitle());
        return postDTO;
    }
}
