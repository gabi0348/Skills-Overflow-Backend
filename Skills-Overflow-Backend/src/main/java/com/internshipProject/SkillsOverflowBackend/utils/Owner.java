package com.internshipProject.SkillsOverflowBackend.utils;

import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;

public class Owner {

    public static boolean isPrincipalOwnerOfPost(User user, Post post) {
        return user != null && user.getUserName().equals(post.getUser().getUserName());
    }

    public static boolean isPrincipalOwnerOfComment(User user, Comment comment) {
        return user != null && user.getUserName().equals(comment.getUser().getUserName());
    }
}
