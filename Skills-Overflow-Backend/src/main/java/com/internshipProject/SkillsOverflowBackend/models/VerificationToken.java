package com.internshipProject.SkillsOverflowBackend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class VerificationToken {

    private static final int EXPIRATION = 60*3;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String token;
    private LocalDateTime expirationDate;

    //this class is the owning side; this refers to the fk, which it annotates
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn
    private User user;

    public VerificationToken(String token) {
        this.token = token;
        this.expirationDate = calculateExpiryDate(EXPIRATION);
    }

    private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes){
//        return LocalDateTime.now().plusMinutes(0);
        return LocalDateTime.now().plusSeconds(60*10);
    }

}
