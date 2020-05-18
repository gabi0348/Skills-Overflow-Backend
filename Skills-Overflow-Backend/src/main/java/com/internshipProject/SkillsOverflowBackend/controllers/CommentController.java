package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.CommentRepository;
import com.internshipProject.SkillsOverflowBackend.services.CommentService;
import com.internshipProject.SkillsOverflowBackend.services.PostService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import com.internshipProject.SkillsOverflowBackend.shouldI.Notification;
import com.internshipProject.SkillsOverflowBackend.shouldI.NotificationRepository;
import com.internshipProject.SkillsOverflowBackend.utils.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    NotificationRepository notificationRepository;

    @PostMapping(value = "addComment/{postId}/{userId}")
    public Comment addComment(@RequestBody @Valid Comment comment, @PathVariable Long postId,
                              @PathVariable Long userId) {
        Optional<Post> optionalPost = postService.findById(postId);
        User user = userService.findById(userId);

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setNumberOfComments(post.getNumberOfComments() + 1L);

            //ost.getComments().stream().map(Comment::getUser).forEach(u -> System.out.println("--------------" + u));

            Stream<User> stream = post.getComments()
                    .stream()
                    .map(Comment::getUser); //doar userii
            Set<User> userList = Stream.concat(Stream.of(post.getUser()), stream)
                    .filter(u -> !(u.getFirstName().equals(user.getFirstName())))
                    .collect(Collectors.toSet());

            Notification notification = new Notification();
            notification.setUsers(userList);
            notification.setNotification("You are notified that something was posted");
            notification.setPost(post);
            notificationRepository.save(notification);

            comment.setUser(user);
            comment.setPost(post);
            commentService.save(comment);

//            userService.saveUser(user);

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

    @DeleteMapping (value = "deleteComment/{commentId}")
    public String deleteComment(@PathVariable Long id){
        Optional<Comment> comment = commentService.findById(id);

        if(comment.isPresent()){
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

    @GetMapping (value = "getPostWithSortedComments/{postId}/{pageNo}")
    public List<Comment> getPostWithSortedComments(@PathVariable Long postId, @PathVariable Long pageNo){

        Optional<Post> optionalPost = postService.findById(postId);
        if(optionalPost.isPresent()){

            Post post = optionalPost.get();
            //daca sunt mai multe pagini decat am
            int noOfPages = post.getComments().size() / 10 + 1;
            if (pageNo > noOfPages) {
                return new ArrayList<>();
            }

            //daca imi trimite pagina nr.0 (prima)
            int comLimit = 10;
            List<Comment> comments = new ArrayList<>();
            if (pageNo == 0) {
                comLimit = 9;
                comments = post.getComments().
                        stream().
                        filter(Comment::getApprovedComment).
                        collect(Collectors.toList());
            }

            //compar in functie de vote count
            List<Comment> sortedComments = post.getComments()
                    .stream()
                    .sorted(Comparator.comparing(Comment::getVoteCount).reversed())
                    .skip(pageNo*10)
                    .limit(comLimit)
                    .collect(Collectors.toList());

            comments.addAll(sortedComments);
            return comments;
        }

        return new ArrayList<>();
    }
}
