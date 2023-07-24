package com.example.vetclinic.dto;

import com.example.vetclinic.model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    @NotEmpty(message = "Please enter valid name.")
    private String name;

    @NotEmpty(message = "Please enter valid number.")
    @NumberFormat
    private String number;

    @NotEmpty(message = "Please enter valid password.")
    private String password;

    @NotEmpty(message = "Please enter valid watchingPassword.")
    private String watchingPassword;


    @Transient
    private String lastName;
    @Transient
    @Email
    private String email;
    @Transient
    private LocalDate dateBirth;
@Transient
    private String photo;

    public UserDto(User user) {
        this.name=user.getFirstName();
        this.lastName=user.getLastName();
        this.number=user.getPhone();
        this.email=user.getEmail();
        this.dateBirth=user.getDateBirth();
        this.photo=user.getPhotoPath();
    }
}