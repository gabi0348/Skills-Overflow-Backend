package com.internshipProject.SkillsOverflowBackend.services.post_service;


import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.TopicFront;

import java.util.List;
import java.util.Optional;

public interface PostService {
    Optional<Post> findById(Long postId);
    Post updateAndSavePost(Post updatePost, Post post);
    void deletePost(Post post);
    List<Post> getAllFilteredPosts(Integer pageNo, String criteria, TopicFront topic);
    Object[] searchForPosts(String queryParam, Integer pageNo);
    Integer getNumberOfPosts();
    public Post save(Post post);

}
