package com.example.exercise.service;


import com.example.exercise.model.Company;
import com.example.exercise.model.Contact;
import com.example.exercise.model.Country;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {
    List<Contact> listAll();
    Page<Contact> findAll(Pageable pageable);

    Contact findById(Long id);

    Contact create(String name,  Long companyId, Long countryId);

    Contact edit(Long id, String name,  Long companyId, Long countryId);

    void delete(Long id);

    Page<Contact> listContactsByCountries(Country country, Pageable pageable);

    Page<Contact> listContactsByCompanies(Company company,  Pageable pageable);

    Page<Contact> listContactsByCountriesAndCompanies(Country country, Company company,  Pageable pageable);

    List<Contact> findContactsByCountries(Country country);

    List<Contact> findContactsByCompanies(Company company);

    List<Contact> findContactsByCountriesAndCompanies(Country country, Company company);
}
