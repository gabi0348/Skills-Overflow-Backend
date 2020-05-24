package com.internshipProject.SkillsOverflowBackend.convertors;

import com.internshipProject.SkillsOverflowBackend.dto.CommentDTO;
import com.internshipProject.SkillsOverflowBackend.models.Comment;

public class CommentConverter {

    public static CommentDTO commentConverter(Comment comment){

        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setId(comment.getId());
        commentDTO.setUserName(comment.getUser().getUserName());
        commentDTO.setBody(comment.getBody());
        commentDTO.setCreateDate(comment.getCreateDate().toLocalDate().toString());
        commentDTO.setVoteCount(comment.getVoteCount());

        return commentDTO;
    }
}
