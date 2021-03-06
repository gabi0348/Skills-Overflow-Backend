package com.internshipProject.SkillsOverflowBackend.controllers;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.dto.LoginDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserDTO;
import com.internshipProject.SkillsOverflowBackend.enums.UsersRoles;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.NotificationRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.user_service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private NotificationRepository notificationRepository;

    @GetMapping("/users")
    public List<UserDTO> findAll() {
        return userService.findAllDto();
    }

    @GetMapping("/allUsers")
    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    @GetMapping("/allAdmins")
    public List<UserDTO> findAllAdmins(){
        return userService.findAllUsersByRole(UsersRoles.ADMIN.toString());
    }

    @GetMapping("/allApprovedUsers")
    public List<UserDTO> findAllApprovedUsers(){
        return userService.findAllUsersByRole(UsersRoles.APPROVED_USER.toString());
    }

    @GetMapping("/allPendingUsers")
    public List<UserDTO> findAllPendingUsers(){
        return userService.findAllUsersByRole(UsersRoles.PENDING_USER.toString());
    }

    @GetMapping("/allBlockedUsers")
    public List<UserDTO> findAllBlockedUsers(){
        return userService.findAllUsersByRole(UsersRoles.BLOCKED_USER.toString());
    }

    @GetMapping("/allDeclinedUsers")
    public List<UserDTO> findAllDeclinedUsers(){
        return userService.findAllUsersByRole(UsersRoles.DECLINED_USER.toString());
    }

    //acum intoarce un string
    @PostMapping("/signUp")
    @ResponseBody
    public String addUser(@RequestBody @Valid User user) {
        return userService.addUser(user);
    }


    @GetMapping("/findById/{id}")
    @ResponseBody
    public UserDTO getById(@PathVariable(value = "id") Long id) {
        return userService.getUserDtoById(id);
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public Map<Object, Object> login(@RequestBody LoginDTO user) {//@PathVariable String username, @PathVariable String password
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToke=new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        Authentication authentication   = authenticationManager.authenticate(usernamePasswordAuthenticationToke);
        String role =authentication.getAuthorities().toString();
        String token = jwtTokenProvider.createToken(user.getEmail(),role);
        Map<Object, Object> model = new HashMap<>();
        model.put("email",jwtTokenProvider.getEmail(token));
        //model.put("email", email);
        model.put("token", token);
        model.put("role",role);
        return model;
    }
    @GetMapping("/user")
    public Authentication returnUser() {
        return SecurityContextHolder.getContext().getAuthentication();
    }





}