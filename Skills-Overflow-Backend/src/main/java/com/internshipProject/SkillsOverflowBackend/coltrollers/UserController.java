package com.internshipProject.SkillsOverflowBackend.coltrollers;


import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    List<User> all() {
        return userService.list();
    }

    @GetMapping("/user")
    public Authentication returnUser() {
        return SecurityContextHolder.getContext().getAuthentication();

    }


    @PostMapping("/signUp")
    @ResponseBody
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/findById/{id}")
    @ResponseBody
    public User getById(@PathVariable(value = "id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/remove/{id}")
    public void removeUser(@PathVariable Long id) {
        userService.removeUserById(id);
    }

    @PatchMapping("/update/{id}")
    @ResponseBody
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    /*
        @PostMapping("/login")
        @ResponseBody
        public Boolean loin(@RequestBody User user){
            return true;
        }*/
    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Authentication login(@RequestParam String username,@RequestParam String password) {//@PathVariable String username, @PathVariable String password
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToke=new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToke);
        } catch (Exception e) {

        }
        return authentication;
    }


/*
    @PostMapping("/login")
    @ResponseBody
    public Boolean login(@RequestBody User user) {
        return userService.checkForExistingEmailandPassword(user.getEmail(),user.getPassword());
    }
*/


}