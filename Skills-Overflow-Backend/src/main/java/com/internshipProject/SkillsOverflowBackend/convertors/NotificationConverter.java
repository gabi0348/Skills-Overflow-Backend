package com.internshipProject.SkillsOverflowBackend.convertors;

import com.internshipProject.SkillsOverflowBackend.dto.NotificationDTO;
import com.internshipProject.SkillsOverflowBackend.models.Notification;

public class NotificationConverter {
    public static NotificationDTO convertToNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setNotificationString("Someone commented on this post: ");
        notificationDTO.setPostName(notification.getPost().getTitle());
        notificationDTO.setPostURL("http://localhost:8081//singlePost/" + notification.getPost().getId());
        notificationDTO.setPostDate(notification.getDate().toLocalDate().toString());
        return notificationDTO;
    }
}
