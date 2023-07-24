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
@Table(name = "staffers")
public class Staffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = true)
    private String fullName;

    @Column(nullable = true, length = 64)
    protected String photos;

    @Column(nullable = true)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "staffer_specialization",
            joinColumns = {@JoinColumn(name = "staffer_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "specialization_id", referencedColumnName = "id")}
    )
    protected List<Specialization> specializations = new ArrayList<>();


    @Transient
    public String getPhotosPath(String uploadPath) {

        if (this.photos == null || this.id == null) return "";

        return "/"+uploadPath+"/"+this.photos;
    }

}