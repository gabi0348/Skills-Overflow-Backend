package com.internshipProject.SkillsOverflowBackend.repositories;

import com.internshipProject.SkillsOverflowBackend.models.BlockedUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockedUserTokenRepository extends JpaRepository<BlockedUserToken, Long> {

}
