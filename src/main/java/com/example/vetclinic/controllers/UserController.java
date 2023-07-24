package com.example.vetclinic.controllers;


import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.model.User;
import com.example.vetclinic.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Value("${upload.path}")
    private String uploadPath;


    @GetMapping("/anketa")
    public String dd(){
        return "anketa";
    }


    @GetMapping("/profile/")
    public ModelAndView registrationForm() {

        User user=userService.getAuthUser();
        user.setPhotosPath(uploadPath);



        return new ModelAndView("profile","user",user);
    }


    @GetMapping("/profile/edit")
    public String editForm( Model model) {

        User user=userService.getAuthUser();
        user.setPhotosPath(uploadPath);

        UserDto userDto = new UserDto(user);
        model.addAttribute("user",userDto);


        return "profile_edit";
    }


    @PostMapping("/update")
    public RedirectView saveUser(
                                 @RequestParam("image") MultipartFile multipartFile) throws IOException {


        if (!multipartFile.isEmpty()) {

            User us=userService.uploadUserPhoto(userService.getAuthUser(),multipartFile ,uploadPath);

            return new RedirectView("/user/profile/", true);
        }
        return new RedirectView("/error12", true);

    }
}