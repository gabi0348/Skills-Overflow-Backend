package com.internshipProject.SkillsOverflowBackend.services;

import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.Notification;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public void generateNotification(Post post, User user){
        Stream<User> stream = post.getComments()
                .stream()
                .map(Comment::getUser); //doar userii
        Set<User> userList = Stream.concat(Stream.of(post.getUser()), stream)
                .filter(u -> !(u.getUserId().equals(user.getUserId())))
                .collect(Collectors.toSet());

        Notification notification = new Notification();
        notification.setUsers(userList);
        notification.setPost(post);
        notification.setNotification("Someone commented on this post");
        notificationRepository.save(notification);
    }

}
