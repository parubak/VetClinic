package com.example.vetclinic.controllers;


import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.model.User;
import com.example.vetclinic.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

@Controller
public class LoginController{

    @Autowired
    UserServiceImpl userService;


    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }


    @GetMapping("/admin/registration")
    public String registrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "admin/registration";
    }

    @PostMapping("/admin/registration")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByPhoneNumber(userDto.getNumber());

        if (existingUser != null)
            result.rejectValue("number", null,
                    "Користувач уже зареєстрований!!!");
        if (!Objects.equals(userDto.getPassword(),userDto.getWatchingPassword()))
            result.rejectValue("watchingPassword", null,
                    "Паролі не співпадають!!!");
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "admin/registration";
        }

        userService.saveUserRegistration(userDto);

        return "redirect:/admin/registration?success";
    }
}