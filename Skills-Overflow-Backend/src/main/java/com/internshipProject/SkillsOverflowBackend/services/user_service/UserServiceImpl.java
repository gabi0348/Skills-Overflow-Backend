package com.internshipProject.SkillsOverflowBackend.services.user_service;

import com.internshipProject.SkillsOverflowBackend.configuration.JwtTokenProvider;
import com.internshipProject.SkillsOverflowBackend.convertors.UserConverter;
import com.internshipProject.SkillsOverflowBackend.dto.LoginDTO;
import com.internshipProject.SkillsOverflowBackend.dto.UserDTO;
import com.internshipProject.SkillsOverflowBackend.enums.UsersRoles;
import com.internshipProject.SkillsOverflowBackend.models.Role;
import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.ResetPasswordTokenRepository;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import com.internshipProject.SkillsOverflowBackend.services.MailService;
import com.internshipProject.SkillsOverflowBackend.services.role_service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResetPasswordTokenRepository resetPasswordTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    private List<UserDTO> usersDto = new ArrayList<>();
    private Role userRole = new Role();


    @Override
    public void convertAllUsers(List<User> usersList) {
        usersDto.clear();
        usersList = userRepository.findAll();
        for (User user : usersList) {
            UserDTO userDto = UserConverter.convertToUserDto(user);
            this.usersDto.add(userDto);
        }
    }

    @Override
    public List<UserDTO> findAllDto() {
        usersDto.clear();
        List<User> usersList = userRepository.findAll();
        convertAllUsers(usersList);
        return this.usersDto;
    }


    @Override
    public List<UserDTO> findAllUsersByRole(String userRole){
        usersDto.clear();
        List<User> usersList = userRepository.findAll();
        for(User user : usersList) {
            UserDTO userDto = UserConverter.convertToUserDto(user);
            if(userDto.getRole().equals(userRole)){
                usersDto.add(userDto);
            }
        }
        return usersDto;
    }

    @Override
    public String addUser(User user) {
        if (checkForExistingEmail(user.getEmail())) {
            return "email already taken";
        } else if (checkForExistingUsername(user.getUserName())) {
            return "username already taken";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.getRoleByRoleName(UsersRoles.PENDING_USER.toString()));
        userRepository.saveAndFlush(user);

        return "Request Pending";
    }

    @Override
    public String findByEmailAndSendResetPasswordEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (user.getResetPasswordToken() != null) {
                return "Check your email. Password reset link already sent";
            }
            new Thread(() -> mailService.resetPasswordMail(user)).start();
            //mailService.resetPasswordMail(user);
            return "Email found";
        }
        return "No email found";

    }


    @Override
    public String userExists(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());

        if (user != null && user.getPassword().equals(loginDTO.getPassword()))
            return "user exists";
        else
            return "user does not exist";
    }

    @Override
    public UserDTO getUserDtoById(Long id) {
        User user = userRepository.getOne(id);
        UserDTO userDto = UserConverter.convertToUserDto(user);
        return userDto;
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean checkForExistingEmail(String email) {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkForExistingUsername(String username) {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveUser(User user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public User resetPassword(String token, User user) {
        User existingUser = resetPasswordTokenRepository.findByToken(token).getUser();
        existingUser.setPassword(user.getPassword());
        userRepository.saveAndFlush(existingUser);
        return existingUser;
    }

    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDTO findBySessionToken() {
        User existingUser = findByEmail(jwtTokenProvider.getUser().getEmail());
        return UserConverter.convertToUserDto(existingUser);
    }
}



