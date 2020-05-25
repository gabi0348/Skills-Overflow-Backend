package com.internshipProject.SkillsOverflowBackend.services.comment_service;


import com.internshipProject.SkillsOverflowBackend.dto.CommentDTO;
import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.User;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
    Comment updateComment(Comment oldComment, Comment newComment);
    void deleteComment(Long id);
    String likeOrDislikeComm(String how, User user, Comment comment);
    List<CommentDTO> getAllUnapprovedComments();
}
