package com.internshipProject.SkillsOverflowBackend.coltrollers;


import com.internshipProject.SkillsOverflowBackend.Configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map login(@RequestParam String email,@RequestParam String password) {//@PathVariable String username, @PathVariable String password
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToke=new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication   = authenticationManager.authenticate(usernamePasswordAuthenticationToke);
        List<String> roles =authentication.getAuthorities().stream().map(x-> x.getAuthority()).collect(Collectors.toList());
        String token = jwtTokenProvider.createToken(email,roles);
        Map<Object, Object> model = new HashMap<>();
        model.put("email", email);
        model.put("token", token);
        model.put("roles",roles);
        return model;
    }


/*
    @PostMapping("/login")
    @ResponseBody
    public Boolean login(@RequestBody User user) {
        return userService.checkForExistingEmailandPassword(user.getEmail(),user.getPassword());
    }
*/


}