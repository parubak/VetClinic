package com.example.vetclinic.controllers;

import com.example.vetclinic.model.Specialization;
import com.example.vetclinic.model.Staffer;
import com.example.vetclinic.repo.SpecializationRepository;
import com.example.vetclinic.repo.StafferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequestMapping("/staffer")
@Controller

public class StafferController {

    @Value("${upload.path}")
    private String uploadPath;
    @Autowired
    StafferRepository stafferRepository;
    @Autowired
    SpecializationRepository specializationRepository ;
    @GetMapping("/list")
    public String stafferList( Model model){

        List<Staffer> staffers=stafferRepository.findAll();
        model.addAttribute("staffers",staffers);
        model.addAttribute("path", "/"+uploadPath+"/staffer/");


        return "staffer/staffers_list";
    }

    @GetMapping("/add")
    public String stafferAdd( Model model){

        Staffer staffer=new Staffer();
        model.addAttribute("specializations", specializationRepository.findAll());
        model.addAttribute("staffer",staffer);


        return "staffer/staffer_add";
    }

    @PostMapping(value = "/add")
    public String save(Staffer staffer) {
        System.out.println("staffer = " + staffer);
        stafferRepository.save(staffer);
        return "redirect:/staffer/add";
    }

    @GetMapping("/edit/{id}")
    public String stafferEdit( @PathVariable Long id,Model model){


        Staffer staffer=stafferRepository.getReferenceById(id);
        model.addAttribute("specializations", specializationRepository.findAll());
        model.addAttribute("staffer",staffer);
        model.addAttribute("photo",
                "/"+uploadPath+"/staffer/"+ staffer.getPhotos());




        return "staffer/staffer_edit";
    }
    @PostMapping(value = "/edit")
    public String edit(Staffer staffer) {
        System.out.println("staffer = " + staffer);
        stafferRepository.save(staffer);
        return "redirect:/staffer/list";
    }


    @GetMapping("/specialization/add")
    public String specializationAddGet( Model model){

        Specialization specialization =new Specialization();

        model.addAttribute("specialization",specialization);


        return "staffer/specialization_add";
    }


    @PostMapping("/specialization/add")
    public String SpecializationAddPost(Specialization specialization) throws IOException {

        specializationRepository.save(specialization);

        return "redirect:/specialization/add";
    }

}
