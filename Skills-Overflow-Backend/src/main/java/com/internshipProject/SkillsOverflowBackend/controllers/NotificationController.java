package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.dto.NotificationDTO;
import com.internshipProject.SkillsOverflowBackend.repositories.NotificationRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
public class NotificationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    NotificationService notificationService;

    @Autowired
    NotificationRepository notificationRepository;

    @GetMapping("/notifications")
    public List<NotificationDTO> findAll() {
        String email= jwtTokenProvider.getUser().getEmail();
         List<NotificationDTO> notificationDTOS= notificationService.findAllNotificationsDTO(email);
        return notificationDTOS;
    }

    @PutMapping("/deleteNotif/{id}")
    public void deleteNotification(@PathVariable Long id){
        notificationService.deleteNotification(id);
    }
}
