package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public Comment save(Comment comment){
        return commentRepository.save(comment);
    }

    public Optional<Comment> findById(Long id){return commentRepository.findById(id);}

    public Comment updateComment(Comment oldComment, Comment newComment){
        oldComment.setBody(newComment.getBody());
        commentRepository.save(oldComment);
        return oldComment;
    }
<<<<<<< HEAD

    public void deleteComment(Long id){
        commentRepository.deleteById(id);
    }
=======
>>>>>>> 044dafd99b89a31c42427de2b22027699418fba6
}
