package com.example.vetclinic.controllers;


import com.example.vetclinic.dto.UserDto;
import com.example.vetclinic.model.Staffer;
import com.example.vetclinic.model.User;
import com.example.vetclinic.repo.StafferRepository;
import com.example.vetclinic.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {

    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    UserServiceImpl userService;

    @Autowired
    StafferRepository stafferRepository;

    @RequestMapping("/")
    public String homeForm(Model model) {
        List<Staffer> staffers=stafferRepository.findAll();
        model.addAttribute("staffers",staffers);
        model.addAttribute("path", "/"+uploadPath+"/staffer/");

        return "new/index";
    }


    @RequestMapping("/admin")
    public String adminForm() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "admin";
    }


}