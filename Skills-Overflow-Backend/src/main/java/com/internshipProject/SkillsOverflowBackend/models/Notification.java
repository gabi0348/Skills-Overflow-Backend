package com.internshipProject.SkillsOverflowBackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationId;

    @ManyToOne
    @JoinColumn( name = "user_id")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column( updatable = false)
    @CreationTimestamp
    private LocalDateTime createDate;

    private String notificationType;

    boolean isUnread;

    public Notification(User user, Post post, String notificationType, boolean isUnread) {
        this.user = user;
        this.post = post;
        this.notificationType = notificationType;
        this.isUnread = isUnread;
    }
}
