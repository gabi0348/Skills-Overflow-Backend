package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post save(Post post) {
        postRepository.save(post);
        return post;
    }

    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    public Post updateAndSavePost(Post updatePost, Post post) {
        //updatePost.setComments(post.getComments());
        //updatePost.setCreateDate(post.getCreateDate());
        //updatePost.setUser(post.getUser());

        updatePost.setBody(post.getBody());
        updatePost.setTitle(post.getTitle());
        updatePost.setTopic(post.getTopic());
        postRepository.save(updatePost);
        return updatePost;
    }

    public void deletePost(Post post) {
        postRepository.delete(post);
    }


    //https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
    public List<Post> getAllFilteredPosts(Integer pageNo, String criteria, String topic) {

        int noOfPages = postRepository.findAll().size() / 10 + 1;
        if (pageNo > noOfPages) {
            return new ArrayList<>();
        }

        if (topic == null){
            return getPostsWithNoTopic(pageNo);
        }

        Stream<Post> posts = postRepository.findAll()
                .stream()
                .filter(post -> post.getTopic().equals(topic));

        if (criteria.equals("date")) {
            posts.sorted(Comparator.comparing(Post::getCreateDate))
                    .skip(pageNo * 10)
                    .limit(10)
                    .collect(Collectors.toList());
        }

        if (criteria.equals("no of comms")){
            posts.sorted(Comparator.comparing(Post::getNumberOfComments))
                    .skip(pageNo * 10)
                    .limit(10)
                    .collect(Collectors.toList());
        }

        return new ArrayList<>();

    }

    private List<Post> getPostsWithNoTopic(Integer pageNo) {
        return postRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Post::getCreateDate))
                .skip(pageNo*10)
                .limit(10)
                .collect(Collectors.toList());
    }


}
