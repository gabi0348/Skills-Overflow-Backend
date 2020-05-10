package com.internshipProject.SkillsOverflowBackend.controllers;


import com.internshipProject.SkillsOverflowBackend.dto.UserDto;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> findAll() {
        return userService.findAllDto();
    }

    //acum intoarce un string
    @PostMapping("/singUp")
    @ResponseBody
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public UserDto getById(@PathVariable(value = "id") Long id) {
        return userService.getUserDtoById(id);
    }

    @DeleteMapping("/remove/{id}")
    public void removeUser (@PathVariable Long id){
            userService.removeUserById(id);
        }

}