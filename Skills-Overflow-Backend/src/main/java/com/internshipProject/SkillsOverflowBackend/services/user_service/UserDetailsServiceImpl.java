package com.internshipProject.SkillsOverflowBackend.services.user_service;

import com.internshipProject.SkillsOverflowBackend.models.User;
import com.internshipProject.SkillsOverflowBackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User appClient =
                userRepository.findByEmail(email);//.orElseThrow(() -> new UsernameNotFoundException("No user found!"));

        List<GrantedAuthority> grantList = new ArrayList<>();


            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(appClient.getRole().getRole());
            grantList.add(grantedAuthority);


        UserDetails user = new org.springframework.security.core.userdetails.User(appClient.getEmail(), appClient.getPassword(), grantList);
        return user;
    }
}