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

    public static boolean equalStrings(String word1, String word2) { //word2 este queryParam care imi vine

        int mistakesAllowed = 2;
        int allowedMistakenWordLength = word2.length() + 2;
        if (word1.length() <= allowedMistakenWordLength) {
            int count=0;
            for (int i = 0; i < word1.length(); i++) { // go from first to last character index the words
                if (word1.charAt(i) != word2.charAt(i)) { // if this character from word 1 does not equal the character from word 2
                    mistakesAllowed--;
                    if (mistakesAllowed < 0) {
                        return false;
                    }
                }
                else{
                    count++;
                    if (count == word2.length()) return true;
                }
            }
            return true;
        }
        return false;
    }
}
