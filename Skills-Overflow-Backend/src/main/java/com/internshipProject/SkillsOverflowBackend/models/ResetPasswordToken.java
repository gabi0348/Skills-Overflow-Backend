package com.internshipProject.SkillsOverflowBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class ResetPasswordToken {

    public static final int EXPIRATION = 60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;
    private LocalDateTime expirationDate;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn
    private User user;

    public ResetPasswordToken(String token) {
        this.token = token;
        this.expirationDate = calculateExpiryDate(EXPIRATION);
    }

    public LocalDateTime calculateExpiryDate(int expiryTimeInSeconds){
        return LocalDateTime.now().plusSeconds(60*10);
    }
}
