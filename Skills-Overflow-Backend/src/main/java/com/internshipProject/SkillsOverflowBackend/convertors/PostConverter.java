package com.internshipProject.SkillsOverflowBackend.convertors;

import com.internshipProject.SkillsOverflowBackend.dto.PostDTO;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.Topic;

import java.util.stream.Collectors;

public class PostConverter {

    public static PostDTO convertToPostDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setBody(post.getBody());
        postDTO.setCreateDate(post.getCreateDate().toLocalDate().toString());
        postDTO.setNumberOfComments(post.getNumberOfComments());
        postDTO.setTitle(post.getTitle());
        postDTO.setTopics(post.getTopics().stream().map(Topic::getTopic).collect(Collectors.toList()));
        return postDTO;
    }
}
