package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.models.*;

import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;

import com.internshipProject.SkillsOverflowBackend.services.comment_service.CommentService;
import com.internshipProject.SkillsOverflowBackend.services.post_service.PostService;

import com.internshipProject.SkillsOverflowBackend.repositories.UserTopicRepository;

import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import com.internshipProject.SkillsOverflowBackend.utils.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.internshipProject.SkillsOverflowBackend.services.NotificationService;

import javax.validation.Valid;
import java.util.Optional;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;
    @Autowired
    NotificationService notificationService;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserTopicRepository userTopicRepository;

    @PostMapping(value = "addComment/{postId}/{userId}")
    public String addComment(@RequestBody @Valid Comment comment, @PathVariable Long postId,
                             @PathVariable Long userId) {
        Optional<Post> optionalPost = postService.findById(postId);
        User user = userService.findById(userId);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setNumberOfComments(post.getNumberOfComments() + 1L);

            //metoda lu gabi
            notificationService.generateNotification(post, user);

            comment.setUser(user);
            comment.setPost(post);
            commentService.save(comment);

            return "your comment is under review";
        }
        return null;
    }

    @PutMapping(value = "voteMostRelevantComment/{commentId}/{userId}")
    public Comment voteMostRelevantComment(@PathVariable Long commentId, @PathVariable Long userId) {
        User user = userService.findById(userId);
        Optional<Comment> optionalComment = commentService.findById(commentId);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            Long id= user.getUserId();
            List<Topic> topicList = comment.getPost().getTopics();
            for (Topic topic : topicList) {

                UserTopic userTopic =userTopicRepository.findByTopicIdAndUserId(topic.getId(),id);
                if(userTopic != null) {
                    //nu mai e nevoie? userTopic= userTopicRepository.findById(userTopic.getUserTopicId()).get();
                    userTopic.setVoteCount(userTopic.getVoteCount()+1);
                    userTopicRepository.save(userTopic);
                }
                else{
                    UserTopic userTopic1 =new UserTopic();
                    userTopic1.setTopicId(topic.getId());
                    userTopic1.setUserId(id);
                    userTopic1.setVoteCount(1);
                    userTopicRepository.save(userTopic1);
                }
            }

            if (Owner.isPrincipalOwnerOfPost(user, comment.getPost())) {

                comment.setIsMostRelevantComment(Boolean.TRUE);
//                trebuie sa gasesc comentariile acelei postari si sa dau false la acea comentarii

                commentService.save(comment);
                return comment;
            }
        }
        return null;
    }

    @PutMapping(value = "likeComment/{commentId}/{how}")
    public String likeComment(@PathVariable String how, @PathVariable Long commentId) {
        Optional<Comment> optionalComment = commentService.findById(commentId);
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            Long postId = comment.getPost().getId();

            for (LikedComm likedComm : user.getLikedComms()) {
                if (likedComm.getPostId().equals(postId))
                    return "you already voted this comment";
            }

            if (how.equals("up")) comment.setVoteCount(comment.getVoteCount() + 1L);
            if (how.equals("down")) comment.setVoteCount(comment.getVoteCount() - 1L);

            user.getLikedComms().add(new LikedComm(postId));
            userService.saveUser(user);
            commentService.save(comment);
            return "voted";
        }
        return "no comment present";
    }

    @PutMapping(value = "editCommentBody/{userId}")
    public Comment editCommentBody(@RequestBody @Valid Comment newComment, @PathVariable Long userId) {
        User user = userService.findById(userId);
        Optional<Comment> optionalComment = commentService.findById(newComment.getId());

        if (optionalComment.isPresent()) {
            Comment oldComment = optionalComment.get();

            if (Owner.isPrincipalOwnerOfComment(user, oldComment))
                return commentService.updateComment(oldComment, newComment);

        }
        return null;
    }

    @DeleteMapping(value = "deleteComment/{commentId}")
    public String deleteComment(@PathVariable Long id) {
        Optional<Comment> comment = commentService.findById(id);

        if (comment.isPresent()) {
            Comment actualComment = comment.get();
            Post commentedPost = actualComment.getPost();

            if (Owner.isPrincipalOwnerOfComment(commentedPost.getUser(), actualComment)) {

                commentedPost.setNumberOfComments(commentedPost.getNumberOfComments() - 1L);
                commentService.deleteComment(id);

                return "successful delete";
            }
            return "not the owner";
        }
        return "no comment found";
    }

    @GetMapping(value = "allCommentsForUser")
    public List<Comment> getAllCommsForUser() {
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
        return user.getComments()
                .stream()
                .sorted(Comparator.comparing(Comment::getCreateDate).reversed())
                .collect(Collectors.toList());
    }
}
