package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.Configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.PostService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import com.internshipProject.SkillsOverflowBackend.utils.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    //{userId} și @PathVariable Long userId nu mai sunt necesare deci
    @PostMapping(value = "createPost")
    public Post newPost(@RequestBody @Valid Post post) {
//        User user = userService.findById(userId);

        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
        post.setUser(user);
        postService.save(post);
        return post;
    }

    @PutMapping(value = "/editPost/{userId}")
    public String editPostWithId(@RequestBody @Valid Post newPost, @PathVariable Long userId) {

        User user = userService.findById(userId);
        Optional<Post> optionalOldPost = postService.findById(newPost.getId());

        if (optionalOldPost.isPresent()) {
            Post oldPost = optionalOldPost.get();

            if (Owner.isPrincipalOwnerOfPost(user, oldPost)) {
                postService.updateAndSavePost(oldPost, newPost);
                return "Updated post";
            }
            return "This is not the owner";
        }
        return "Post or user not found";
    }

    @DeleteMapping(value = "/deletePost/{userId}")
    public String deletePostWithId(@RequestBody @Valid Post newPost, @PathVariable Long userId) {

        User user = userService.findById(userId);
        Optional<Post> optionalOldPost = postService.findById(newPost.getId());

        if (optionalOldPost.isPresent()) {
            Post post = optionalOldPost.get();

            if (Owner.isPrincipalOwnerOfPost(user, post)) {
                postService.deletePost(post);
                return "Deleted post";
            }
            return "This is not the owner";
        }
        return "Post or user not found";
    }

    //intoarce null daca n-a mai gasit postari; cate postari pe pagina, 10? daca topicul e null, intoarce tot sortat dupa data
    @GetMapping(value = "/allPosts/{pageNo}/{topic}/{criteria}")
    public List<Post> getAllFilteredPosts(@PathVariable Integer pageNo, @PathVariable String criteria,
                                          @PathVariable String topic) {
        return postService.getAllFilteredPosts(pageNo, criteria, topic);
    }

    @GetMapping(value = "/singlePost/{postId}")
    public Post getPost(@PathVariable Long postId){
        Optional<Post> optionalPost = postService.findById(postId);
        return optionalPost.orElse(null);
    }

}


