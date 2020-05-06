package com.internshipProject.SkillsOverflowBackend.repositories;

import com.internshipProject.SkillsOverflowBackend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long > {
}
