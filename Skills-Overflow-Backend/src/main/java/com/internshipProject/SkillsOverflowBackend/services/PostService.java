package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.repositories.PostRepository;
import org.graalvm.compiler.serviceprovider.ServiceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public Post save(Post post){
        postRepository.save(post);
        return post;
    }

    public Optional<Post> findById(Long postId){
        return postRepository.findById(postId);
    }

    public Post updateAndSavePost(Post updatePost, Post post){
        updatePost.setBody(post.getBody());
        updatePost.setComments(post.getComments());
        updatePost.setCreateDate(post.getCreateDate());
        updatePost.setTitle(post.getTitle());
        updatePost.setUser(post.getUser());
        postRepository.save(updatePost);
        return updatePost;
    }

    public void deletePost(Post post){
        postRepository.delete(post);
    }

    //https://howtodoinjava.com/spring-boot2/pagination-sorting-example/
    public List<Post> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Page<Post> pagedResult = postRepository.findAll(paging);

        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<>();
        }

}
