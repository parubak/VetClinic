package com.example.vetclinic.service;


import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IUserService extends UserDetailsService {
    void saveUserRegistration(UserDto userDto);



    User findUserByPhoneNumber(String phoneNumber);

    User uploadUser(User user, MultipartFile multipartFile,String uploadPath) throws IOException;


    User findUserById(long id);

    String getPathPhoto();
}