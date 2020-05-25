package com.internshipProject.SkillsOverflowBackend.services.admin_service;

import com.internshipProject.SkillsOverflowBackend.enums.UsersRoles;
import com.internshipProject.SkillsOverflowBackend.models.Comment;
import com.internshipProject.SkillsOverflowBackend.models.Post;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.BlockedUserTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.CommentRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.PostRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.role_service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlockedUserTokenRepository blockedUserTokenRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public String approveRequest(Long id){
        User existingUser = userRepository.getOne(id);
            existingUser.setRole(roleService.getRoleByRoleName(UsersRoles.APPROVED_USER.toString()));
            mailService.confirmRegistrationMail(existingUser);
            new Thread(() -> mailService.confirmRegistrationMail(existingUser)).start();
            userRepository.saveAndFlush(existingUser);
            return "Please check your email.";
    }

    @Override
    public String declineRequest(Long id) {
        User existingUser = userRepository.getOne(id);
        existingUser.setRole(roleService.getRoleByRoleName(UsersRoles.DECLINED_USER.toString()));
        mailService.declineUserEmail(existingUser);
        userRepository.saveAndFlush(existingUser);
        return "The request has been declined.";
    }

    @Override
    public String blockUser(Long id){
        User existingUser = userRepository.getOne(id);
            existingUser.setRole(roleService.getRoleByRoleName(UsersRoles.BLOCKED_USER.toString()));
            mailService.blockedUserEmail(existingUser);
            userRepository.saveAndFlush(existingUser);
            return "user blocked";

    }

    @Override
    public String unblockUser(Long id) {
        User existingUser = userRepository.getOne(id);
        existingUser.setBlockedUserToken(null);
        existingUser.setRole(roleService.getRoleByRoleName(UsersRoles.APPROVED_USER.toString()));
        existingUser.setBlockCount(0L);
        mailService.unblockedByAdminEmail(existingUser);
        userRepository.saveAndFlush(existingUser);
        return "user unblocked";
    }

    @Override
    public String promoteUserToAdmin(Long id){
        User existingUser = userRepository.getOne(id);
        if(existingUser.getRole().getRole().equals(UsersRoles.APPROVED_USER.toString())){
            existingUser.setRole(roleService.getRoleByRoleName(UsersRoles.ADMIN.toString()));
            mailService.promoteUserToAdminEmail(existingUser);
            userRepository.saveAndFlush(existingUser);
            return "promoted to admin";
        }
        return "conditions not met";
    }

    public String approvePost(Long id) {
        Post existingPost = postRepository.getOne(id);
        if(!existingPost.getIsApproved()){
            existingPost.setIsApproved(true);
            mailService.approvedPostMail(existingPost.getUser());
            postRepository.saveAndFlush(existingPost);
            return "your post has been approved";
        }
        return "cannot approve post";
    }

    public String deletePost(Long id) {
        postRepository.deleteById(id);
        return "post deleted";
    }

    public String approveComment(Long id) {
        Comment existingComment = commentRepository.getOne(id);
        if(!existingComment.getIsApproved()) {
            existingComment.setIsApproved(true);
            existingComment.getPost().setNumberOfComments(existingComment.getPost().getNumberOfComments() + 1);
            mailService.approveCommentMail(existingComment.getUser());
            commentRepository.saveAndFlush(existingComment);
            return "your comment has been approved";
        }
        return "cannot approve comment";
    }

    public String deleteComment(Long id) {
        commentRepository.deleteById(id);
        return "post deleted";
    }
}
