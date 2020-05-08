package com.internshipProject.SkillsOverflowBackend.controllers;


import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping("/users")
    List<User> all() {
        return userService.list();
    }

    @PostMapping("/signUp")
//    @ResponseBody
    public String addUser(@RequestBody User user){
        boolean check = userService.checkForExistingEmailOrUsername(user.getEmail(), user.getEmail());
        if(!check) {
            mailService.confirmRegistration(user);
            return "pending request";
        }
        else {
            return "email or username already taken";
        }
    }

    @GetMapping("/findById/{id}")
//    @ResponseBody
    public User getById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable Long id){
        userService.removeUserById(id);
    }

    @PatchMapping("/update/{id}")
//    @ResponseBody
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }


}