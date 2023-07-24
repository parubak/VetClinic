package com.example.vetclinic.repo;


import com.example.vetclinic.model.Specialization;
import com.example.vetclinic.model.Staffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends JpaRepository<Specialization, Long> {

}