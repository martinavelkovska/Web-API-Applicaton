package com.example.exercise.model;

import lombok.*;

import jakarta.persistence.*;

@Getter
@Setter
@ToString
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;

    private String Name;

    public Country(String name){
        this.Name = name;
    }

    public Country() {

    }
}
