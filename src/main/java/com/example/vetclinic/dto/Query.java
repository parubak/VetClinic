package com.example.vetclinic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Query {
    String fullName;
    String orderDates;
    String orderPhone;
    String orderTime;
    String orderAnimal;
    String orderDoctor;
    String orderContent;

}
