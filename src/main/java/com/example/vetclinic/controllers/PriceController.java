package com.example.vetclinic.controllers;

import com.example.vetclinic.dto.Query;
import com.example.vetclinic.model.Service;
import com.example.vetclinic.repo.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/admin/price")
public class PriceController {
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping()
    public String priceList(Model model) {

        List<Service> price = serviceRepository.findAll();
        Query query = new Query();

        model.addAttribute("query", query);
        model.addAttribute("price", price);

        return "price/price_list";
    }

    @GetMapping("/add")
    public String priceAdd(Model model) {

        Service service = new Service();
        model.addAttribute("service", service);

        return "price/price_add";
    }

    @PostMapping("/add")
    public String priceAddPost(Service service) {

        serviceRepository.save(service);

        return "redirect:/admin/price";
    }

    @GetMapping("/edit/{id}")
    public String priceEdit(@PathVariable Long id, Model model){

        Service service=serviceRepository.getReferenceById(id);
        model.addAttribute("service",service);

        return "price/price_edit";
    }

    @PostMapping(value = "/edit")
    public String edit(Service service) {

        serviceRepository.save(service);

        return "redirect:/admin/price/edit/"+service.getId()+"?save";
    }

    @GetMapping("/delete/{id}")
    public String priceDelete(@PathVariable Long id){

        serviceRepository.deleteById(id);

        return "redirect:/admin/price";
    }
}
