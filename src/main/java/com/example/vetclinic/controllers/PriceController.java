package com.example.vetclinic.controllers;

import com.example.vetclinic.model.Service;
import com.example.vetclinic.model.Staffer;
import com.example.vetclinic.repo.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class PriceController {
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/admin/price")
    public String priceList(Model model) {
        System.out.println("price");

        List<Service> price = serviceRepository.findAll();

        model.addAttribute("price", price);
        return "price";
    }

    @GetMapping("/admin/price/add")
    public String priceAdd(Model model) {
        System.out.println("price");

        Service service = new Service();

        model.addAttribute("service", service);
        return "price_add";
    }
    @PostMapping("/admin/price/add")
    public String priceAddPost(Service service) {

        serviceRepository.save(service);
        return "redirect:/admin/price";
    }

    @GetMapping("/admin/price/edit/{id}")
    public String priceEdit(@PathVariable Long id, Model model){

        Service service=serviceRepository.getReferenceById(id);
        model.addAttribute("service",service);

        return "price_edit";
    }
    @PostMapping(value = "/admin/price/edit")
    public String edit(Service service) {
        serviceRepository.save(service);
        return "redirect:/admin/price";
    }
    @GetMapping("/admin/price/delete/{id}")
    public String priceDelete(@PathVariable Long id){

        serviceRepository.deleteById(id);
        return "redirect:/admin/price";
    }

}
