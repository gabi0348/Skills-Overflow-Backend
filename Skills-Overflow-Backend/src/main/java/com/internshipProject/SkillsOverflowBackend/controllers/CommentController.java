package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.models.*;

import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.comment_service.CommentService;
import com.internshipProject.SkillsOverflowBackend.services.post_service.PostService;
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
    CommentService commentServiceImpl;
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
            commentServiceImpl.save(comment);

            return "your comment is under review";
        }
        return null;
    }

    @PutMapping(value = "voteMostRelevantComment/{commentId}/{userId}")
    public Comment voteMostRelevantComment(@PathVariable Long commentId, @PathVariable Long userId) {
        User user = userService.findById(userId);
        Optional<Comment> optionalComment = commentServiceImpl.findById(commentId);

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();

            if (Owner.isPrincipalOwnerOfPost(user, comment.getPost())) {

                comment.setIsMostRelevantComment(Boolean.TRUE);
//                trebuie sa gasesc comentariile acelei postari si sa dau false la acea comentarii

                commentServiceImpl.save(comment);
                return comment;
            }
        }
        return null;
    }

    @PutMapping (value = "voteComment/{commentId}/{how}")
    public String voteComment(@PathVariable String how, @PathVariable Long commentId) {
        Optional<Comment> optionalComment = commentServiceImpl.findById(commentId);
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            Long postId = comment.getPost().getId();

            for (VotedComm votedComm: user.getVotedComms()){
                if (votedComm.getPostId().equals(postId)) return "you already voted";
            }

            if (how.equals("up")) comment.setVoteCount(comment.getVoteCount() + 1L);
            if (how.equals("down")) comment.setVoteCount(comment.getVoteCount() - 1L);

            user.getVotedComms().add(new VotedComm(postId));
            userService.saveUser(user);
            commentServiceImpl.save(comment);
            return "voted";
        }
        return "no comment present";
    }

    @PutMapping (value = "editCommentBody/{userId}")
    public Comment editCommentBody(@RequestBody @Valid Comment newComment, @PathVariable Long userId) {
        User user = userService.findById(userId);
        Optional<Comment> optionalComment = commentServiceImpl.findById(newComment.getId());

        if (optionalComment.isPresent()) {
            Comment oldComment = optionalComment.get();

            if(Owner.isPrincipalOwnerOfComment(user, oldComment))
                return commentServiceImpl.updateComment(oldComment, newComment);
        }
        return null;
    }

    @DeleteMapping (value = "deleteComment/{commentId}")
    public String deleteComment(@PathVariable Long id){
        Optional<Comment> comment = commentServiceImpl.findById(id);

        if(comment.isPresent()){
            Comment actualComment = comment.get();
            Post commentedPost = actualComment.getPost();

            if (Owner.isPrincipalOwnerOfComment(commentedPost.getUser(), actualComment)) {

                commentedPost.setNumberOfComments(commentedPost.getNumberOfComments() - 1L);
                commentServiceImpl.deleteComment(id);

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
                        filter(Comment::getIsMostRelevantComment).
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

    @GetMapping (value = "allCommentsForUser")
    public List<Comment> getAllCommsForUser(){
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
        return user.getComments()
                .stream()
                .sorted(Comparator.comparing(Comment::getCreateDate).reversed())
                .collect(Collectors.toList());
    }
}
