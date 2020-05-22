package com.internshipProject.SkillsOverflowBackend.convertors;

import com.internshipProject.SkillsOverflowBackend.dto.PostDTO;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.Topic;

import java.time.LocalDateTime;
import java.util.List;

public class PostConverter {

    private Long postId;
    private String title;
    private String body;
    private Long numberOfComments;
    private LocalDateTime createDate;
    private List<Topic> topicList;

    public static PostDTO convertToPostDTO(Post post){
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getId());
        postDTO.setUserName(post.getUser().getUserName());
        postDTO.setTitle(post.getTitle());
        postDTO.setBody(post.getBody());
        postDTO.setNumberOfComments(post.getNumberOfComments());
        postDTO.setCreateDate(post.getCreateDate());
        postDTO.setTopicList(post.getTopics());

        return postDTO;
    }
}
