package com.internshipProject.SkillsOverflowBackend.services.comment_service;

import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.LikedComm;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.CommentRepository;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserService userService;

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public Optional<Comment> findById(Long id){return commentRepository.findById(id);}

    public Comment updateComment(Comment oldComment, Comment newComment){
        oldComment.setBody(newComment.getBody());
        commentRepository.save(oldComment);
        return oldComment;
    }

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }

    public String likeOrDislikeComm(String how, User user, Comment comment) {
        Long postId = comment.getPost().getId();

        for (LikedComm likedComm : user.getLikedComms()) {
            if (likedComm.getPostId().equals(postId))
                return "you already voted this comment";
        }

        if (how.equals("up")) comment.setVoteCount(comment.getVoteCount() + 1L);
        if (how.equals("down")) comment.setVoteCount(comment.getVoteCount() - 1L);

        user.getLikedComms().add(new LikedComm(postId));
        userService.saveUser(user);
        save(comment);
        return "voted";
    }


}
