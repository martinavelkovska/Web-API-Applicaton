package com.example.exercise.model;


import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@ToString
@Entity
public class Company {

    @Id
    @GeneratedValue
    private Long Id;

    @Column
    private String Name;

    public Company(String companyName){
        this.Name = companyName;
    }

    public Company() {

    }

}
