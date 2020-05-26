package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.dto.PostCreatedDTO;
import com.internshipProject.SkillsOverflowBackend.dto.SinglePostDTO;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.TopicFront;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.PostRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.post_service.PostService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    PostRepository postRepository;
    @Autowired
    JwtTokenProvider jwtTokenProvider;



    @PostMapping(value = "/createPost")
    public String newPost(@RequestBody PostCreatedDTO postCreatedDTO) {
        postService.save(postService.convertCreatedPostDTOToPost(postCreatedDTO));
        return "your post is under review";
    }


    //intoarce null daca n-a mai gasit postari; daca topicul e null, intoarce tot sortat dupa data
    @PostMapping(value = "/allPosts/{pageNo}/{criteria}")
    public Object[] getAllFilteredPosts(@PathVariable Integer pageNo, @PathVariable String criteria,
                                        @RequestBody TopicFront topic) {
        Object[] arr = new Object[2];

        Stream<Post> postDTOStream = postRepository.findAll().stream();
        //all posts counted, regardless of page number, depending if the topic array is empty or not
        arr[0] = (topic.getTopics().length > 0)
                ? (int) postService.getPostWithTopicStream(topic, postDTOStream).count()
                : postRepository.findAll().stream().filter(Post::getIsApproved).count();
        arr[1] = postService.getAllFilteredPosts(pageNo, criteria, topic);

        return arr;
    }

    //i'm returning the comment, the posts, and IF this actual user is the owner of the post
    @GetMapping(value = "/singlePost/{postId}")
    public SinglePostDTO getPost(@PathVariable Long postId) {
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
        return postService.getSinglePostWithComments(postId, user);
    }

    @GetMapping(value = "/allPostsForUser")
    public List<Post> getAllPosts() {
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
        return user.getPosts().stream()
                .sorted(Comparator.comparing(Post::getCreateDate).reversed())
                .collect(Collectors.toList());
    }

    //aici trebuie post fiindca asa e metoda din front-end
    @PostMapping(value = "/allSearchedPosts/{pageNo}/{searchParam}/{criteria}")
    public Object[] getSearchedPosts(@PathVariable Integer pageNo, @PathVariable String searchParam,
                                     @PathVariable String criteria, @RequestBody TopicFront topicFront) {
        return postService.searchForPosts(searchParam, pageNo, criteria, topicFront);
    }


}


