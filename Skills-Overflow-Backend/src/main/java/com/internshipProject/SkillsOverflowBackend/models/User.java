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
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@EnableAutoConfiguration
//@Accessors(chain = true)
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
    @Size(min = 5, max = 100)
    @NotBlank
    //@Pattern(regexp = "[A-Za-z0-9]*")
    private String password;

    private String firstName;

    private String lastName;

    private Boolean enabled;

    private Boolean changedPassword = false;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private VerificationToken verificationToken;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private ResetPasswordToken resetPasswordToken;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;


    @ManyToMany(mappedBy = "users")
    private List<Notification> notifications;

    public List<Notification> getUnreadNotifications() {
        notifications.stream()
                .filter(item -> item.isUnread)
                .collect(Collectors.toList());
        return notifications;
    }
}

