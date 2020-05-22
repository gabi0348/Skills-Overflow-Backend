package com.internshipProject.SkillsOverflowBackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikedComm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long postId;

    @ManyToOne
    User user;

    public LikedComm(Long postId){
        this.postId = postId;
    }
}
