package com.example.exercise.model;

import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Contact {
    @Id
    @GeneratedValue
    @Column
    private  Long Id;

    @Column
    private String Name;

    @ManyToOne
    private Company Company;

    @ManyToOne(cascade = CascadeType.ALL)
    private Country Country;

    public Contact(String name, Company companyId, Country countryId){
        this.Name = name;
        this.Company = companyId;
        this. Country = countryId;
    }

    public Contact() {

    }
}
