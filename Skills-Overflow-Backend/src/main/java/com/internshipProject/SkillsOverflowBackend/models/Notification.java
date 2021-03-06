package com.internshipProject.SkillsOverflowBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    //am sters cascade type all
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "notification_user",
            joinColumns = {@JoinColumn(name = "notification_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users;

    //si aici am modificat - cand stergi useri, stergi notificari si stergi si postari
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime date;

    private String senderName;

    private int notificationType;
    //1 - cineva a comentat
    //2 - mi-a fost votat raspunsul cu cel mai bun raspuns
    //3- bravo! ai devenit scrum master

    //boolean isUnread;
    private String topics;

    public void addUser(User user) {
        if (users == null) {
            users = new HashSet<>();
        }
        users.add(user);
    }

}
