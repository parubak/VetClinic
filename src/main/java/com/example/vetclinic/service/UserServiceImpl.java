package com.example.vetclinic.service;


import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.model.Role;
import com.example.vetclinic.model.User;
import com.example.vetclinic.repo.RoleRepository;
import com.example.vetclinic.repo.UserRepository;
import com.example.vetclinic.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.ADMIN);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.ADMIN));

        User user = new User(userDto.getName(), userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()),Arrays.asList(role));
// User user = new User(userDto.getName(),"Lastt",userDto.getEmail(),passwordEncoder.encode(userDto.getPassword()),"1334","date",Arrays.asList(role));


        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}