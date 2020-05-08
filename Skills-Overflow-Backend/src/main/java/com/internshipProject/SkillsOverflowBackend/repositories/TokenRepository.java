package com.internshipProject.SkillsOverflowBackend.repositories;

import com.internshipProject.SkillsOverflowBackend.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

}
