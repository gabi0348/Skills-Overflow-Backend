package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.models.*;
import com.internshipProject.SkillsOverflowBackend.repositories.CommentRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserTopicRepository;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.NotificationService;
import com.internshipProject.SkillsOverflowBackend.services.comment_service.CommentService;
import com.internshipProject.SkillsOverflowBackend.services.post_service.PostService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import com.internshipProject.SkillsOverflowBackend.utils.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    CommentRepository commentRepository;
    @Autowired
    UserTopicRepository userTopicRepository;
    @Autowired
    MailService mailService;


    @PostMapping(value = "addComment/{postId}")
    public String addComment(@RequestBody @Valid Comment comment, @PathVariable Long postId) {
        Optional<Post> optionalPost = postService.findById(postId);
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());

        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();

            comment.setUser(user);
            comment.setPost(post);
            commentService.save(comment);

            return "your comment is under review";
        }
        return "no posts here";
    }

//    @PutMapping(value = "voteMostRelevantComment/{commentId}")
//    public Comment voteMostRelevantComment(@PathVariable Long commentId) {
//        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
//        Optional<Comment> optionalComment = commentService.findById(commentId);
//
//        if (optionalComment.isPresent()) {
//            Comment comment = optionalComment.get();
//            if (Owner.isPrincipalOwnerOfPost(user, comment.getPost())) {
//
//                Long id= user.getUserId();
//                List<Topic> topicList = comment.getPost().getTopics();
//                for (Topic topic : topicList) {
//
//                    UserTopic userTopic =userTopicRepository.findByTopicIdAndUserId(topic.getId(),id);
//                    if (userTopic != null) {
//                        //nu mai e nevoie? userTopic= userTopicRepository.findById(userTopic.getUserTopicId()).get();
//                        userTopic.setVoteCount(userTopic.getVoteCount()+1);
//                        userTopicRepository.save(userTopic);
//                    }
//                    else {
//                        UserTopic userTopic1 =new UserTopic();
//                        userTopic1.setTopicId(topic.getId());
//                        userTopic1.setUserId(id);
//                        userTopic1.setVoteCount(1);
//                        userTopicRepository.save(userTopic1);
//                    }
//                }
//
//                comment.setIsMostRelevantComment(Boolean.TRUE);
//                commentService.save(comment);
//                return comment;
//            }
//        }
//        return null;
//    }

//    @PutMapping(value = "unVoteMostRelevantComment/{commentId}")
//    public Comment unVoteMostRelevantComment(@PathVariable Long commentId) {
//    User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
//    Optional<Comment> optionalComment = commentService.findById(commentId);
//
//        if (optionalComment.isPresent()) {
//        Comment comment = optionalComment.get();
//
//        if (Owner.isPrincipalOwnerOfPost(user, comment.getPost())) {
//
//            //verific daca mai exista UN comentariu cu mostRelevantComment = true
//            List<Comment> oneComment = commentRepository.findAll()
//                        .stream()
//                        .filter(Comment::getIsMostRelevantComment)
//                        .collect(Collectors.toList());
//                if (oneComment.size() != 0) {
//                    oneComment.get(0).setIsMostRelevantComment(false);
//                    commentRepository.save(comment);
//                }
//                else { return new Comment();}
//
//            Long id= user.getUserId();
//            List<Topic> topicList = comment.getPost().getTopics();
//            for (Topic topic : topicList) {
//
//                UserTopic userTopic =userTopicRepository.findByTopicIdAndUserId(topic.getId(),id);
//                if (userTopic != null) {
//                    //nu mai e nevoie? userTopic= userTopicRepository.findById(userTopic.getUserTopicId()).get();
//                    userTopic.setVoteCount(userTopic.getVoteCount()-1);
//                    userTopicRepository.save(userTopic);
//                }
//                else {
//                    System.out.println("This topic is not associated with this user");
//            Long id = user.getUserId();
//            List<Topic> topicList = comment.getPost().getTopics();
//            for (Topic topic : topicList) {
//
//                UserTopic userTopic = userTopicRepository.findByTopicIdAndUserId(topic.getId(), id);
//                if (userTopic != null) {
//                    //nu mai e nevoie? userTopic= userTopicRepository.findById(userTopic.getUserTopicId()).get();
//                    userTopic.setVoteCount(userTopic.getVoteCount() + 1);
//                    userTopicRepository.save(userTopic);
//                } else {
//                    UserTopic userTopic1 = new UserTopic();
//                    userTopic1.setTopicId(topic.getId());
//                    userTopic1.setUserId(id);
//                    userTopic1.setVoteCount(1);
//                    userTopicRepository.save(userTopic1);
//                }
//            }
//
//            comment.setIsMostRelevantComment(Boolean.FALSE);
//            commentService.save(comment);
//            return comment;
//            }
//        }
//        return null;
//    }


    @PutMapping(value = "likeComment/{commentId}/{how}")
    public String likeComment(@PathVariable String how, @PathVariable Long commentId) {
        Optional<Comment> optionalComment = commentService.findById(commentId);
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());

        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            return commentService.likeOrDislikeComm(how, user, comment);
        }
        return "no comment present";
    }

    @PutMapping(value = "editCommentBody/{userId}")
    public Comment editCommentBody(@RequestBody @Valid Comment newComment, @PathVariable Long userId) {
        User user = userService.findById(userId);
        Optional<Comment> optionalComment = commentService.findById(newComment.getCommentId());

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
                postService.save(commentedPost);

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

    @PutMapping(value = "toggleVoteComment/{commentId}")
    public Comment unVoteMostRelevantComment(@PathVariable Long commentId) {
        User user = userRepository.findByEmail(jwtTokenProvider.getUser().getEmail());
        Comment comment = commentRepository.getOne(commentId);
        List<Topic> topicList = comment.getPost().getTopics();

        if (Owner.isPrincipalOwnerOfPost(user, comment.getPost())) {
            if (comment.getIsMostRelevantComment()) {
                comment.setIsMostRelevantComment(false);
                commentRepository.saveAndFlush(comment);
                for (Topic topic : topicList) {
                    UserTopic userTopic = userTopicRepository.findByTopicIdAndUserId(topic.getId(), user.getUserId());
                    if (userTopic != null) {
                        userTopic.setVoteCount(userTopic.getVoteCount() - 1);
                        userTopicRepository.save(userTopic);
                    }
                }
            } else if (!comment.getIsMostRelevantComment()) {
                Post post = comment.getPost();
                for (Comment comment1 : post.getComments()) {
                    if (comment1.getIsMostRelevantComment()) {
                        return comment;
                    }
                }
                comment.setIsMostRelevantComment(true);
                notificationService.generateVoteNotification(post,comment.getUser());
                new Thread(() -> mailService.receivedVote(comment.getUser(),post.getUser().getUserName(),post.getTitle(),post.getId())).start();

                commentRepository.saveAndFlush(comment);
                for (Topic topic : topicList) {
                    UserTopic userTopic = userTopicRepository.findByTopicIdAndUserId(topic.getId(), user.getUserId());
                    if (userTopic != null) {
                        userTopic.setVoteCount(userTopic.getVoteCount() + 1);
                        userTopicRepository.save(userTopic);
                    }
                }
            }
            return  comment;
        }
        return null;

    }
}
