package com.internshipProject.SkillsOverflowBackend.dto;

import com.internshipProject.SkillsOverflowBackend.models.Topic;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PostDTO {

    private Long postId;
    private String userName;
    private String title;
    private String body;
    private Long numberOfComments;
    private LocalDateTime createDate;
    private List<Topic> topicList;


}
