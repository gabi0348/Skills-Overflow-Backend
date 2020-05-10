package com.internshipProject.SkillsOverflowBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
    private String userName;
    @NotNull
    private String email;
    @NotNull
    private String password;

    private String firstName;
    private String lastName;

    private Boolean enabled;

    @OneToOne(mappedBy = "user")
    private VerificationToken verificationToken;

    @OneToOne(mappedBy = "user")
    private ResetPasswordToken resetPasswordToken;

    @ManyToMany
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;




}
