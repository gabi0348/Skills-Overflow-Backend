package com.internshipProject.SkillsOverflowBackend.dto;


import com.internshipProject.SkillsOverflowBackend.models.Role;
import lombok.*;
import lombok.experimental.Accessors;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long userId;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private String role;

}
