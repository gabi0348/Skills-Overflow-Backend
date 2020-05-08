package com.internshipProject.SkillsOverflowBackend.dto;


import com.internshipProject.SkillsOverflowBackend.models.Role;
import lombok.*;
import lombok.experimental.Accessors;

import org.springframework.stereotype.Service;

import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Accessors(chain = true)
@Service
public class UserDto {

    private Long userId;
    private String userName;
    private String email;
    private String firstName;
    private String lastName;
    private Set<Role> roles;

}
