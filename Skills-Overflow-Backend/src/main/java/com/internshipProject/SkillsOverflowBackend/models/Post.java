package com.internshipProject.SkillsOverflowBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.internshipProject.SkillsOverflowBackend.convertors.CommentConverter;
import com.internshipProject.SkillsOverflowBackend.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(length = 700)
    private String body;
    private Long numberOfComments = 0L;

    //nullable = false: l-am scos pentru teste, nu am reusit sa inserez manual
    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    private Boolean isApproved = false;

    //merge sau persist
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> comments;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<Notification> notifications;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "post_topic",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "topic_id")}
    )
    private List<Topic> topics;


    public List<CommentDTO> getApprovedComments() {
        List<Comment> comments = getComments();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getIsApproved()) {
                commentDTOS.add(CommentConverter.commentConverter(comment));
            }

        }
        return commentDTOS;
    }

}