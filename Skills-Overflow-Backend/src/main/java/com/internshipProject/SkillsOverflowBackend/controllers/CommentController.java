package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.NotificationRepository;
import com.internshipProject.SkillsOverflowBackend.services.CommentService;
import com.internshipProject.SkillsOverflowBackend.services.NotificationService;
import com.internshipProject.SkillsOverflowBackend.services.PostService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import com.internshipProject.SkillsOverflowBackend.utils.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    NotificationService notificationService;

    @PostMapping(value = "addComment/{postId}/{userId}")
    public Comment addComment(@RequestBody @Valid Comment comment, @PathVariable Long postId,
                              @PathVariable Long userId) {
        Optional<Post> optionalPost = postService.findById(postId);
        User user = userService.findById(userId);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setNumberOfComments(post.getNumberOfComments() + 1L);

            notificationService.generateNotification( post, user);

            comment.setUser(user);
            comment.setPost(post);
            commentService.save(comment);

            return comment;
        }
        return null;
    }

    @PutMapping(value = "approveComment/{commentId}/{userId}")
    public Comment approveComment(@PathVariable Long commentId, @PathVariable Long userId) {
        User user = userService.findById(userId);
        Optional<Comment> optionalComment = commentService.findById(commentId);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            if (Owner.isPrincipalOwnerOfPost(user, comment.getPost())) {

                comment.setApprovedComment(Boolean.TRUE);

                commentService.save(comment);
                return comment;
            }
        }
        return null;
    }

    @PutMapping (value = "voteComment/{commentId}/{how}")
    public Comment voteComment(@PathVariable String how, @PathVariable Long commentId) {
        Optional<Comment> optionalComment = commentService.findById(commentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if (how.equals("up")) comment.setVoteCount(comment.getVoteCount() + 1L);
            if (how.equals("down")) comment.setVoteCount(comment.getVoteCount() - 1L);

            commentService.save(comment);
            return comment;
        }
        return null;
    }

    @PutMapping (value = "editCommentBody/{userId}")
    public Comment editCommentBody(@RequestBody @Valid Comment newComment, @PathVariable Long userId) {
        User user = userService.findById(userId);
        Optional<Comment> optionalComment = commentService.findById(newComment.getId());

        if (optionalComment.isPresent()) {
            Comment oldComment = optionalComment.get();

            if(Owner.isPrincipalOwnerOfComment(user, oldComment))
                return commentService.updateComment(oldComment, newComment);
        }
        return null;
    }
}
