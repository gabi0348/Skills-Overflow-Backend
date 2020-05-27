package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.convertors.NotificationConverter;
import com.internshipProject.SkillsOverflowBackend.dto.NotificationDTO;
import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.Notification;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.NotificationRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    public void generateNotification(Post post, User user){
        Set<User> users = post.getComments()
                .stream()
                .map(Comment::getUser)
                .filter(filterUser -> !user.getUserId().equals(filterUser.getUserId()))
                .collect(Collectors.toSet());

        Notification notification = new Notification();
        notification.setUsers(users);
        notification.setPost(post);
        notification.setNotificationType(1);
        notification.setSenderName(user.getUserName());
        notificationRepository.save(notification);
    }

    public void generateVoteNotification(Post post, User user) {
        Notification notification = new Notification();
        notification.addUser(user);
        notification.setPost(post);
        notification.setNotificationType(1);
        notification.setSenderName(user.getUserName());
        notificationRepository.save(notification);
    }

    private List<NotificationDTO> notificationDTOS = new ArrayList<>();

    public void convertAllNotifications(List<Notification> notificationList) {
        notificationDTOS.clear();

        for (Notification notification : notificationList) {
            NotificationDTO notificationDTO = NotificationConverter.convertToNotificationDTO(notification);
            this.notificationDTOS.add(notificationDTO);
        }
    }


    public List<NotificationDTO> findAllNotificationsDTO(String email) {
        notificationDTOS.clear();
        List<Notification> notificationList = userRepository.findByEmail(email).getNotifications();
        convertAllNotifications(notificationList);
        return notificationDTOS;
    }

    public void deleteNotification(Long id){
        User user = jwtTokenProvider.getUser();
        Notification notificationToBeRemoved = notificationRepository.findByNotificationId(id);
        notificationToBeRemoved.getUsers().remove(user);
        notificationRepository.saveAndFlush(notificationToBeRemoved);
    }

}
