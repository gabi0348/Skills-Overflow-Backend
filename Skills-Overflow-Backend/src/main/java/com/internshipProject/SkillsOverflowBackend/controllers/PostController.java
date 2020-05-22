package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.Configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.TopicFront;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.PostService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import com.internshipProject.SkillsOverflowBackend.utils.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public String newPost(@RequestBody @Valid Post post) {
        //aici trebuie sa dai mail cu mail service

        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
        post.setUser(user);
        postService.save(post);
        return "your post is under review";
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
    //acum din front-end poti lua atat postarile, cat si numarul de postari;
    //pot trimite un array de postari din front-end? trebuie modificat si front-endul
    @PostMapping(value = "/allPosts/{pageNo}/{criteria}")
    public Object[] getAllFilteredPosts(@PathVariable Integer pageNo, @PathVariable String criteria,
                                          @RequestBody TopicFront topic) {
        Object[] arr = new Object[2];
        arr[0] = postService.getAllFilteredPosts(pageNo, criteria, topic).size();
        arr[1] = postService.getAllFilteredPosts(pageNo, criteria, topic);
        return arr;
    }

    @GetMapping(value = "/singlePost/{postId}")
    public void getPost(@PathVariable Long postId){
        Optional<Post> optionalPost = postService.findById(postId);
        //return optionalPost.orElse(null); de returnat obiect care sa zice dace acel user e owner (boolean)

        //check
    }

    @GetMapping(value = "/allPostsForUser")
    public List<Post> getAllPosts(){
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
        return user.getPosts().stream()
                .sorted(Comparator.comparing(Post::getCreateDate).reversed())
                .collect(Collectors.toList());
    }

    @PostMapping (value = "/allSearchedPosts/{pageNo}/{searchParam}")
    public Object[] getSearchedPosts(@PathVariable Integer pageNo, @PathVariable String searchParam){
        return postService.searchForPosts(searchParam, pageNo);
    }

}


