package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.PostRepository;
import com.internshipProject.SkillsOverflowBackend.services.PostService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;


    @GetMapping(value = "createPost/{userId}")
    public Post newPost(@RequestBody @Valid Post post, @PathVariable Long userId){
        User user = userService.findById(userId);
        post.setUser(user);
        postService.save(post);
        return post;
    }

    @GetMapping(value = "/editPost/{userId}")
    public String editPostWithId(@RequestBody @Valid Post newPost, @PathVariable Long userId) {

            User user = userService.findById(userId);
            Optional<Post> optionalOldPost = postService.findById(newPost.getId());

            if (optionalOldPost.isPresent()) {
                Post oldPost = optionalOldPost.get();

                if (isPrincipalOwnerOfPost(user, oldPost)) {
                    postService.updateAndSavePost(oldPost, newPost);
                    return "Updated post";
                }
                return "This is not the owner";
            }
            return "Post or user not found";
    }

    @DeleteMapping (value = "/deletePost/{userId}")
    public String deletePostWithId(@RequestBody @Valid Post newPost, @PathVariable Long userId) {

        User user = userService.findById(userId);
        Optional<Post> optionalOldPost = postService.findById(newPost.getId());

        if (optionalOldPost.isPresent()) {
            Post post = optionalOldPost.get();

            if (isPrincipalOwnerOfPost(user, post)) {
                postService.deletePost(post);
                return "Deleted post";
            }
            return "This is not the owner";
        }
        return "Post or user not found";
    }

    @GetMapping (value ="/allPosts")
        public List<Post> getAllPosts(){

        }

    private boolean isPrincipalOwnerOfPost(User user, Post post) {
        return user!= null && user.getUserName().equals(post.getUser().getUserName());
    }

}
