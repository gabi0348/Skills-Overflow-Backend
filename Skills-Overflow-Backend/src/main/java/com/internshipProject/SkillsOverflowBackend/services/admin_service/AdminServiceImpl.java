package com.internshipProject.SkillsOverflowBackend.services.admin_service;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.BlockedUserTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
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

    @Override
    public String approveRequest(Long id, User user){
        User existingUser = userRepository.getOne(id);
            existingUser.setRole(user.getRole());
            mailService.confirmRegistrationMail(existingUser);
            new Thread(() -> mailService.confirmRegistrationMail(existingUser)).start();
            userRepository.saveAndFlush(existingUser);
            return "Please check your email.";
    }

    @Override
    public String declineRequest(Long id, User user) {
        User existingUser = userRepository.getOne(id);
        existingUser.setRole(user.getRole());
        mailService.declineUserEmail(existingUser);
        userRepository.saveAndFlush(existingUser);
        return "The request has been declined.";
    }

    @Override
    public String blockUser(Long id, User user){
        User existingUser = userRepository.getOne(id);
            existingUser.setRole(user.getRole());
            mailService.blockedUserEmail(existingUser);
            userRepository.saveAndFlush(existingUser);
            return "user blocked";

    }

    public String unblockUser(Long id, User user) {
        User existingUser = userRepository.getOne(id);
        existingUser.setBlockedUserToken(user.getBlockedUserToken());
        existingUser.setRole(user.getRole());
        existingUser.setBlockCount(user.getBlockCount());
        mailService.unblockedByAdminEmail(existingUser);
        userRepository.saveAndFlush(existingUser);
        return "user unblocked";
    }

    @Override
    public String promoteUserToAdmin(Long id, User user){
        User existingUser = userRepository.getOne(id);
        if(existingUser.getRole().getRole().equals("approved user")){
            existingUser.setRole(user.getRole());
            mailService.promoteUserToAdminEmail(existingUser);
            userRepository.saveAndFlush(existingUser);
            return "promoted to admin";
        }
        return "conditions not met";
    }
}
