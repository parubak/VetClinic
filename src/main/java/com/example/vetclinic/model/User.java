package com.example.vetclinic.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {



   private transient   String photoPath;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = true)
    protected String email;
    @Column(nullable = false)
    protected String password;

    @Column(nullable = true)
    protected String firstName;

    @Column(nullable = true)
    protected String lastName;

    @Column(nullable = true, length = 64)
    protected String photos;

    @Column(nullable = true)
    protected LocalDate dateBirth;


    @Column(nullable = false, unique = true)
    protected String phone;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    protected List<Role> roles = new ArrayList<>();

    public User(String firstName, String last_name, String email, String password, String phone_number, LocalDate dateBirth, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = last_name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.dateBirth = dateBirth;
        this.phone = phone_number;

    }

    public  User(String name, String email, String encode, List<Role> asList) {
    this.firstName =name;
    this.email=email;
    this.password=encode;
    this.roles=asList;
    }

    @Transient
    public void setPhotosPath(String uploadPath) {

        if (photos == null || this.id == null) return;

       this.photoPath= "/"+uploadPath+"/"+this.id+"/"+this.photos;
    }
}