package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.models.BlockedUserToken;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.admin_service.AdminService;
import com.internshipProject.SkillsOverflowBackend.services.blocked_user_token_service.BlockedUserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    BlockedUserTokenService blockedUserTokenService;

    @PutMapping("/approveRequest/{id}")
    @ResponseBody
    public String savePassword(@PathVariable Long id, @RequestBody User user) {
        adminService.approveRequest(id, user);
        return "Request approved";
    }

    @DeleteMapping("/declineRequest/{id}")
    @ResponseBody
    public String declineRequest(@PathVariable Long id){
        adminService.declineRequestAndDeleteUser(id);
        return "The request has been denied. User has been deleted";
    }

    @PutMapping("/blockUser/{id}")
    @ResponseBody
    public String blockUser(@PathVariable Long id, @RequestBody User user){
        adminService.blockUser(id, user);
        return "User has been blocked";
    }

    @PutMapping("/promoteToAdmin/{id}")
    @ResponseBody
    public String promoteUserToAdmin(@PathVariable Long id, @RequestBody User user){
        String message = adminService.promoteUserToAdmin(id, user);
        return message;
    }
}
