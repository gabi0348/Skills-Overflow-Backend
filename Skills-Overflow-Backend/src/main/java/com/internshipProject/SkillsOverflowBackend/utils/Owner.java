package com.internshipProject.SkillsOverflowBackend.utils;

import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;
import org.apache.commons.lang3.ArrayUtils;

import java.util.HashMap;

public class Owner {

    public static boolean isPrincipalOwnerOfPost(User user, Post post) {
        return user != null && user.getUserName().equals(post.getUser().getUserName());
    }

    public static boolean isPrincipalOwnerOfComment(User user, Comment comment) {
        return user != null && user.getUserName().equals(comment.getUser().getUserName());
    }

    public static boolean equalStrings(String topicName, String queryParam) { //queryParam este queryParam care imi vine

        int mistakesAllowed = 1; //adica doua
        int allowedMistakenWordLength = queryParam.length() + 2;
        if (topicName.length() <= allowedMistakenWordLength) {
            int count=0;
            for (int i = 0; i < topicName.length(); i++) { // go from first to last character index the words
                if (topicName.charAt(i) != queryParam.charAt(i)) { // if this character from word 1 does not equal the character from word 2
                    mistakesAllowed--;
                    if (mistakesAllowed < 0) {
                        return false;
                    }
                }
                else {
                    count++;
                    if (count == queryParam.length()) return true;
                }
            }
            return true;
        }
        return false;
    }
}
