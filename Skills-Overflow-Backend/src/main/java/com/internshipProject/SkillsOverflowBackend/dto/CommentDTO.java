package com.internshipProject.SkillsOverflowBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private Long id;
    private String userName;
    private String body;
    private String createDate;
    private Long voteCount;
}
