package com.internshipProject.SkillsOverflowBackend.convertors;

import com.internshipProject.SkillsOverflowBackend.dto.NotificationDTO;
import com.internshipProject.SkillsOverflowBackend.models.Notification;

public class NotificationConverter {
    public static NotificationDTO convertToNotificationDTO(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        if (notification.getNotificationType() == 1) {
            notificationDTO.setNotificationString(notification.getSenderName() + " commented on this post: ");
            notificationDTO.setPostName(notification.getPost().getTitle());
            notificationDTO.setPostURL("singlePost/" + notification.getPost().getId());
        } else if (notification.getNotificationType() == 2) {
            notificationDTO.setNotificationString(notification.getSenderName() + " voted your comment as the best answer on this post: ");
            notificationDTO.setPostName(notification.getPost().getTitle());
            notificationDTO.setPostURL("singlePost/" + notification.getPost().getId());
        }else if(notification.getNotificationType()==3) {
            notificationDTO.setNotificationString( "Congrats! You are now master in "+notification.getTopics() + ". Check for ");
            notificationDTO.setPostName("new questions");
            notificationDTO.setPostURL("dashboard" );
        }
        notificationDTO.setPostDate(notification.getDate().toLocalDate().toString());
        notificationDTO.setNotificationType(notification.getNotificationType());
        return notificationDTO;
    }
}
