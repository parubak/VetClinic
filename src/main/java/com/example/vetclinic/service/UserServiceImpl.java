package com.example.vetclinic.service;


import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.model.Role;
import com.example.vetclinic.model.User;
import com.example.vetclinic.repo.RoleRepository;
import com.example.vetclinic.repo.UserRepository;
import com.example.vetclinic.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    public UserServiceImpl(){
    }
    @Override
    public void saveUserRegistration(UserDto userDto) {
        if (!Objects.equals(userDto.getPassword(),userDto.getWatchingPassword()))
            throw new RuntimeException("Паролі не співпадають!!!");

        Role role = roleRepository.findByName(TbConstants.Roles.ADMIN);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.ADMIN));

        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        User user=User.builder().firstName(userDto.getName())
                .number(userDto.getNumber())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(List.of(role))
                .build();

        userRepository.save(user);
        System.out.println("userDto = " + userDto+"saveUserRegistrationEnd");
    }


    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        System.out.println("phoneNumber = " + phoneNumber+"findUserByPhoneNumber");
        return userRepository.findFirstByNumber(phoneNumber);
    }




    @Override
    public User uploadUser(User user, MultipartFile multipartFile, String uploadPath) throws IOException {

        return null;
    }



    public User uploadUserPhoto(User user, MultipartFile multipartFile, String uploadPath) throws IOException {

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        User userDb = findUserByPhoneNumber(user.getNumber());
        fileName = user.getId() + "_" + UUID.randomUUID()+fileName;

        String delPhoto = userDb.getNumber();
        userDb.setNumber(fileName);


        FileUploadUtil.saveFile(Paths.get(uploadPath, userDb.getId().toString()).toString()
                , fileName, multipartFile, delPhoto);

        userRepository.save(userDb);
        return userDb;
    }

    @Override
    public User findUserById(long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public String getPathPhoto() {
        return null;
    }

    public User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findFirstByNumber(auth.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrNumber) throws UsernameNotFoundException {


        System.out.println("usernameOrNumber = " + usernameOrNumber+"loadUserByUsername");

        User user = userRepository.findFirstByNumber(usernameOrNumber);
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getNumber()
                    ,user.getPassword(),
                    user.getRoles().stream()
                            .map((role) -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()));
        } else {
            throw new UsernameNotFoundException("Invalid number or password");
        }
    }






}