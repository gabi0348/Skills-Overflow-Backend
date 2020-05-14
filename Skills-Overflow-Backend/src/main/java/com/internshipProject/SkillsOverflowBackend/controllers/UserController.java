package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.dto.LoginDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserDTO;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/users")
    public List<UserDTO> findAll() {
        return userService.findAllDto();
    }

    @GetMapping("/allUsers")
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    //acum intoarce un string
    @PostMapping("/signUp")
    @ResponseBody
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }

    @PostMapping("/logIn")
    @ResponseBody
    public String logIn(@RequestBody LoginDTO user){
            return userService.userExists(user);
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public UserDTO getById(@PathVariable(value = "id") Long id) {
        return userService.getUserDtoById(id);
    }

    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable Long id) {
        userService.removeUserById(id);
    }

}