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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Size(min = 5, max = 200)
    @NotBlank
//    @Pattern(regexp = "[A-Za-z0-9]*")
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
    @JoinColumn( name = "role_id")

    private Role role;


}

