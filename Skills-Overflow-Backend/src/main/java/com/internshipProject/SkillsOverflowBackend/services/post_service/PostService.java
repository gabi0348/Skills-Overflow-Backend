package com.internshipProject.SkillsOverflowBackend.services.post_service;


import com.internshipProject.SkillsOverflowBackend.dto.PostCreatedDTO;
import com.internshipProject.SkillsOverflowBackend.dto.PostDTO;
import com.internshipProject.SkillsOverflowBackend.dto.SinglePostDTO;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.TopicFront;
import com.internshipProject.SkillsOverflowBackend.models.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface PostService {
    Optional<Post> findById(Long postId);

    List<PostDTO> getAllFilteredPosts(Integer pageNo, String criteria, TopicFront topic);

    Post save(Post post);

    SinglePostDTO getSinglePostWithComments(Long postId, User user);

    Stream<Post> getPostWithTopicStream(TopicFront topic, Stream<Post> allPosts);

    List<PostDTO> getFilteredAndSortedPostDTOS(Integer pageNo, String criteria, TopicFront topic, Stream<Post> allPosts);

    Object[] searchForPosts(String queryParam, Integer pageNo, String criteria, TopicFront topic);

    List<PostDTO> getAllUnapprovedPosts();

    Post convertCreatedPostDTOToPost(PostCreatedDTO postCreatedDTO);
}

