package com.internshipProject.SkillsOverflowBackend.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class ResetPasswordToken {

    private static final int EXPIRATION = 60*3;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resetId;

    private String token;
    private LocalDateTime expirationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private User user;

    public ResetPasswordToken(String token) {
        this.token = token;
//        this.expirationDate = calculateExpiryDate(EXPIRATION);
    }

//    private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes){
//        return LocalDateTime.now().plusMinutes(expiryTimeInMinutes);
//    }
}
