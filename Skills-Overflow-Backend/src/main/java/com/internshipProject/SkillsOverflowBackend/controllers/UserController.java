package com.internshipProject.SkillsOverflowBackend.controllers;


import com.internshipProject.SkillsOverflowBackend.dto.UserDto;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users")
    public List<UserDto> findAll() {
        return userService.findAllDto();
    }

    @GetMapping("/allUsers")
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    //acum intoarce un string
    @PostMapping("/signUp")
    @ResponseBody
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PostMapping("/resetPassword")
    public User resetPassword(@RequestParam("email") String email){
        return userService.findByEmail(email);
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