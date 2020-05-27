package com.internshipProject.SkillsOverflowBackend.services.user_profile_service;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.convertors.UserProfilePostConverter;
import com.internshipProject.SkillsOverflowBackend.dto.ResetPasswordDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserProfilePostDTO;
import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.comment_service.CommentService;
import com.internshipProject.SkillsOverflowBackend.services.post_service.PostService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;


    private List<UserProfilePostDTO> userProfilePostDTOList = new ArrayList<>();
    private List<UserProfilePostDTO> listWithPostsWhereUserPostedComment = new ArrayList<>();
    private List<Comment> commentList = new ArrayList<>();

    @Override
    public String resetPassword(ResetPasswordDTO resetPasswordDTO){
        User existingUser = userService.findByEmail(jwtTokenProvider.getUser().getEmail());

        if(passwordEncoder.matches(resetPasswordDTO.getOldPassword(), existingUser.getPassword())){
            existingUser.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
        } else {
            return "old password doesn't match the input";
        }
        userService.saveUser(existingUser);
        return "password successfully changed";
    }

    @Override
    public String changeUsername(String username){
        User existingUser = userService.findByEmail(jwtTokenProvider.getUser().getEmail());
        existingUser.setUserName(username);
        userService.saveUser(existingUser);
        return "username successfully changed";
    }

    @Override
    public String changeFirstAndLastName(User user){
        User existingUser = userService.findByEmail(jwtTokenProvider.getUser().getEmail());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        userService.saveUser(existingUser);
        return "first name and last name successfully changed";
    }

    @Override
    public List<UserProfilePostDTO> getUserPosts(){
        userProfilePostDTOList.clear();
        User existingUser = userService.findByEmail(jwtTokenProvider.getUser().getEmail());
        for(Post post : existingUser.getPosts()){
            if(post != null) {
                UserProfilePostDTO userProfilePostDTO = UserProfilePostConverter.convertToUserProfilePostDTO(post);
                userProfilePostDTOList.add(userProfilePostDTO);
            }
        }
        return  userProfilePostDTOList;
    }

    @Override
    public List<UserProfilePostDTO> getPostsWhereUserPostedComment(){
        listWithPostsWhereUserPostedComment.clear();
        User existingUser = userService.findByEmail(jwtTokenProvider.getUser().getEmail());
        for(Comment comment : existingUser.getComments()){
            if(comment != null) {
                listWithPostsWhereUserPostedComment.add(UserProfilePostConverter.convertToUserProfilePostDTO(comment.getPost()));
            }
        }
        return listWithPostsWhereUserPostedComment;
    }


}
