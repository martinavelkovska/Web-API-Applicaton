package com.example.exercise.service;

import com.example.exercise.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompanyService {

    Page<Company> findAll(Pageable pageable);
    List<Company> listAll();

    Company findById(Long id);

    Company create(String CompanyName);

    Company edit(Long id, String CompanyName);

    void delete(Long id);

}
