package com.internshipProject.SkillsOverflowBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    Long id;
    private String title;
    private String body;
    private Long numberOfComments;
    private String createDate;
    private List<String> topics;

}
