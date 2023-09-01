package com.example.vetclinic.controllers;


import com.example.vetclinic.dto.Query;
import com.example.vetclinic.model.Service;
import com.example.vetclinic.model.Staffer;
import com.example.vetclinic.repo.ServiceRepository;
import com.example.vetclinic.repo.StafferRepository;
import com.example.vetclinic.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    @Autowired
    ServiceRepository serviceRepository;

    @RequestMapping("/")
    public String homeForm(Model model) {

        List<Staffer> staffers=stafferRepository.findAll();
        Query query = new Query();

        model.addAttribute("query", query);
        model.addAttribute("staffers",staffers);
        model.addAttribute("path", "/"+uploadPath+"/staffer/");

        return "index";
    }

    @GetMapping("/service")
    public String priceList(Model model) {

        List<Service> price = serviceRepository.findAll();
        Query query = new Query();

        model.addAttribute("query", query);
        model.addAttribute("price", price);

        return "service";
    }

    @RequestMapping("/about_us")
    public String aboutUs(Model model) {

        Query query = new Query();
        model.addAttribute("query", query);

        return "about_us";
    }

    @GetMapping("/contact")
    public String contact(Model model) {

        Query query = new Query();
        model.addAttribute("query", query);

        return "contact";
    }


}