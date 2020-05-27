package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.dto.CommentDTO;
import com.internshipProject.SkillsOverflowBackend.dto.PostDTO;
import com.internshipProject.SkillsOverflowBackend.services.admin_service.AdminService;
import com.internshipProject.SkillsOverflowBackend.services.comment_service.CommentService;
import com.internshipProject.SkillsOverflowBackend.services.post_service.PostService;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

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

    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable Long id) {
        userService.removeUserById(id);
    }

    @GetMapping("/allUnapprovedComments")
    public List<CommentDTO> findAllUnapprovedComments(){
        return commentService.getAllUnapprovedComments();
    }

    @PutMapping("/approvePost/{id}")
    @ResponseBody
    public String approvePost(@PathVariable Long id) {

        return adminService.approvePost(id);
    }

    @DeleteMapping("/deletePost/{id}")
    public String deletePost(@PathVariable Long id) {
       return adminService.deletePost(id);
    }

    @GetMapping("/allUnapprovedPosts")
    public List<PostDTO> findAllUnapprovedPosts(){
        return postService.getAllUnapprovedPosts();
    }

    @PutMapping("/approveComment/{id}")
    @ResponseBody
    public String approveComment(@PathVariable Long id) {
        return adminService.approveComment(id);
    }

    @DeleteMapping("/deleteComment/{id}")
    public String deleteComment(@PathVariable Long id) {
        return adminService.deleteComment(id);
    }


}
