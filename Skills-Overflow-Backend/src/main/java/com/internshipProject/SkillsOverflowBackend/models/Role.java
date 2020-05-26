package com.internshipProject.SkillsOverflowBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Long roleId;
    private String role;


    public Role(Long roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> users;

}