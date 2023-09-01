package com.example.vetclinic.controllers;

import com.example.vetclinic.model.Specialization;
import com.example.vetclinic.model.Staffer;
import com.example.vetclinic.model.User;
import com.example.vetclinic.repo.SpecializationRepository;
import com.example.vetclinic.repo.StafferRepository;
import com.example.vetclinic.service.FileUploadUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequestMapping("/file")
@Controller

public class FileController {

    @Autowired
    StafferRepository stafferRepository;
    @Autowired
    SpecializationRepository specializationRepository;

    @Value("${upload.path}")
    private String uploadPath;


    @SneakyThrows
    @PostMapping("/edit")
    public RedirectView savePhoto(@RequestParam("id") Long id,
                                  @RequestParam("image") MultipartFile multipartFile) {


        if (!multipartFile.isEmpty()) {

            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

            fileName = id + "_" + UUID.randomUUID() + getFileExtension(fileName);

            Staffer staffer = stafferRepository.getReferenceById(id);
            String delPhoto = staffer.getPhotos();
            staffer.setPhotos(fileName);

            FileUploadUtil.saveFile(uploadPath + "/staffer"
                    , fileName, multipartFile, delPhoto);

            stafferRepository.save(staffer);

            return new RedirectView("/admin/staffer/edit/" + staffer.getId(), true);
        }
        return new RedirectView("/error12", true);
    }

    private String getFileExtension(String str) {
        int index = str.lastIndexOf('.');
        return index == -1 ? null : str.substring(index);
    }
}
