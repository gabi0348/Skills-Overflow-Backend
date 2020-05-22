package com.internshipProject.SkillsOverflowBackend.services.post_service;


import com.internshipProject.SkillsOverflowBackend.dto.PostDTO;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.TopicFront;
import com.internshipProject.SkillsOverflowBackend.models.User;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> findById(Long postId);
    Post updateAndSavePost(Post updatePost, Post post);
    void deletePost(Post post);
    List<PostDTO> getAllFilteredPosts(Integer pageNo, String criteria, TopicFront topic);
    Object[] searchForPosts(String queryParam, Integer pageNo);
    Integer getNumberOfPosts();
    Post save(Post post);
    Object[] getPostWithSortedComments(Long postId, Long pageNo, User user);

}
