package com.example.vetclinic.service;


import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.model.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
}