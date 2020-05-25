package com.internshipProject.SkillsOverflowBackend.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Size(min = 2, max = 20)
    @NotBlank
    private String userName;

    @NotNull
    @NotBlank
    private String email;

    @NotNull(message = "Password cannot be empty")
    @Size(min = 5, max = 200)
    @NotBlank
    private String password;

    private String firstName;

    private String lastName;

    private Boolean enabled = false;

    private Boolean changedPassword = false;

    private Long blockCount = 0L;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private VerificationToken verificationToken;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private ResetPasswordToken resetPasswordToken;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private BlockedUserToken blockedUserToken;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    //??? + json_ignore
    @ManyToMany(mappedBy = "users")
    //@JsonIgnore
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> comments;

    //le-am adaugat eu, merg oare
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Post> posts;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<VotedComm> votedComms;

/*    public List<Notification> getUnreadNotifications() {
        notifications.stream()
                .filter(item -> item.isUnread)
                .collect(Collectors.toList());
        return notifications;
    }*/
}


