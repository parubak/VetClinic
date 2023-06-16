package com.example.vetclinic.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String first_name;

    @Column(nullable = true)
    private String last_name;

    @Column(nullable = true)
    private String date_birth;

    @Column(nullable = true)
    private String phone_number;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")}
    )
    private List<Role> roles = new ArrayList<>();

    public User(String first_name, String last_name, String email, String password, String phone_number, String date_birth, List<Role> roles) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.date_birth = date_birth;
        this.phone_number = phone_number;

    }

    public  User(String name, String email, String encode, List<Role> asList) {
    this.first_name=name;
    this.email=email;
    this.password=encode;
    this.roles=asList;
    }
}