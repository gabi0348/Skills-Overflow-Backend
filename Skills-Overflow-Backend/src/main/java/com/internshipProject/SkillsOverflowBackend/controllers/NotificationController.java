package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.Configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.models.Notification;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
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

    @GetMapping("/notifications")
    public List<Notification> findAll() {
         return userRepository.findByEmail(jwtTokenProvider.getUser().getEmail()).getNotifications();
    }

    @GetMapping("/unreadNotificationsNumber")
    public int findUnreadNotificationsNumber() {

       return userRepository.findByEmail(jwtTokenProvider.getUser().getEmail()).getUnreadNotifications().size();
    }
}
