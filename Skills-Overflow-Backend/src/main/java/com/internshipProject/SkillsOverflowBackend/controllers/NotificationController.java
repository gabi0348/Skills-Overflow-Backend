package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.dto.NotificationDTO;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotificationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/notifications")
    public List<NotificationDTO> findAll() {
        String email= jwtTokenProvider.getUser().getEmail();
         List<NotificationDTO> notificationDTOS= notificationService.findAllNotificationsDTO(email);
        return notificationDTOS;
    }

    @GetMapping("/unreadNotificationsNumber")
    public int findUnreadNotificationsNumber() {

       return userRepository.findByEmail(jwtTokenProvider.getUser().getEmail()).getUnreadNotifications().size();
    }
}
