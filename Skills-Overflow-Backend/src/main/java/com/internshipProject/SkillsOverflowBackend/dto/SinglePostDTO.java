package com.internshipProject.SkillsOverflowBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class SinglePostDTO {
    private PostDTO postDTO;
    private List<CommentDTO> commentDTOList;
    private boolean isPrincipalOwnerOfPost;
}
