package com.internshipProject.SkillsOverflowBackend.controllers;

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
    public String approveRequest(@PathVariable Long id) {
        return adminService.approveRequest(id);
    }

    @PutMapping("/declineRequest/{id}")
    @ResponseBody
    public String declineRequest(@PathVariable Long id){
        return adminService.declineRequest(id);
    }

    @PutMapping("/blockUser/{id}")
    @ResponseBody
    public String blockUser(@PathVariable Long id){
        return adminService.blockUser(id);
    }

    @PutMapping("/unblockUser/{id}")
    @ResponseBody
    public String unblockUser(@PathVariable Long id){
        return adminService.unblockUser(id);
    }

    @PutMapping("/promoteToAdmin/{id}")
    @ResponseBody
    public String promoteUserToAdmin(@PathVariable Long id){
        return adminService.promoteUserToAdmin(id);
    }
}
