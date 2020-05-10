package com.internshipProject.SkillsOverflowBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @ManyToMany
    @JoinTable(
            name = "role_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

//    @OneToMany(mappedBy = "user")
//    private List<Post> posts;

// ---------pentru unit testing, pls nu sterge
    public User(@NotNull String userName, @NotNull String email, @NotNull String password, String firstName, String lastName, Boolean enabled, VerificationToken verificationToken, Set<Role> roles) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = enabled;
        this.verificationToken = verificationToken;
        this.roles = roles;
    }
}
