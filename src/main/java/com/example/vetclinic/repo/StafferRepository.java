package com.example.vetclinic.repo;


import com.example.vetclinic.model.Role;
import com.example.vetclinic.model.Staffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StafferRepository extends JpaRepository<Staffer, Long> {

}