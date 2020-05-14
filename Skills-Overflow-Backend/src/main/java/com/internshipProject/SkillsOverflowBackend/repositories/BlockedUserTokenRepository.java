package com.internshipProject.SkillsOverflowBackend.repositories;

import com.internshipProject.SkillsOverflowBackend.models.BlockedUserToken;
import com.internshipProject.SkillsOverflowBackend.models.ResetPasswordToken;
import com.internshipProject.SkillsOverflowBackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedUserTokenRepository extends JpaRepository<BlockedUserToken, Long> {

    BlockedUserToken findByToken(String token);
    BlockedUserToken findByUser(User user);

}
