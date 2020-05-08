package com.internshipProject.SkillsOverflowBackend.controllers;


import com.internshipProject.SkillsOverflowBackend.dto.UserDto;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MailService mailService;

    @GetMapping("/users")
    public List<UserDto> findAll() {
        return userService.findAllDto();
    }

    @PostMapping("/addUser")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public UserDto getById(@PathVariable(value = "id") Long id) {
        return userService.getUserDtoById(id);
    }

        @PostMapping("/signUp")
        public String addSignUpUser (@RequestBody User user){
            boolean check = userService.checkForExistingEmailOrUsername(user.getEmail(), user.getEmail());
            if (!check) {
                mailService.confirmRegistration(user);
                return "pending request";
            } else {
                return "email or username already taken";
            }
        }

        @DeleteMapping("/remove/{id}")
        public void removeUser (@PathVariable Long id){
            userService.removeUserById(id);
        }



    }