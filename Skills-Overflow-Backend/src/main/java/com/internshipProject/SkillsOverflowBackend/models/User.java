package com.internshipProject.SkillsOverflowBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.internshipProject.SkillsOverflowBackend.shouldI.Notification;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

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
    @Size(min = 5, max = 20)
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]*")
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

    //??? + json_ignore
    @ManyToMany(mappedBy = "users")
    //@JsonIgnore
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Comment> comments;

}
