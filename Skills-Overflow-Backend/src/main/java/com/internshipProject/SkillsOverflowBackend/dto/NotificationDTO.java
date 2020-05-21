package com.internshipProject.SkillsOverflowBackend.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Getter
//@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class NotificationDTO {
    String notificationString;
    String postName;
    String postURL;
    String postDate;

    public NotificationDTO() {
    }
}
