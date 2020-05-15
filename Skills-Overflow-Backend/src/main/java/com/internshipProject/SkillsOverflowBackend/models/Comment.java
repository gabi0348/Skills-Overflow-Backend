package com.internshipProject.SkillsOverflowBackend.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//https://github.com/reljicd/spring-boot-blog/tree/master/src/main/java/com/reljicd
@Table(name = "comment")
@Data
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String body;
    private Long voteCount;
    private Boolean approvedComment;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    @NotNull
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
