package com.example.exercise.service;


import com.example.exercise.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {

    Page<Country> findAll(Pageable pageable);

    List<Country> listAll();

    Country findById(Long id);

    Country create(String name);

    Country edit(Long id, String name);

    void delete(Long id);
}
