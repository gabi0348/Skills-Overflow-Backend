package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.Notification;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public void generateNotifications(Post post){
        Notification notification =new Notification(post.getUser(),post,"raspuns",true);
        List<Notification> notifications;
        notificationRepository.save(notification);


    }
}
