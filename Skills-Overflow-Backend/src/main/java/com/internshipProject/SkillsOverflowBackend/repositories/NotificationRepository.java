package com.internshipProject.SkillsOverflowBackend.repositories;


import com.internshipProject.SkillsOverflowBackend.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository extends JpaRepository <Notification,Long>{

    Optional<Notification> findById(Long notificationId);
}
