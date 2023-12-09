package com.example.exercise.service.impl;

import com.example.exercise.model.Company;
import com.example.exercise.model.Contact;
import com.example.exercise.model.exeptions.CompanyNotFoundException;
import com.example.exercise.repository.CompanyRepository;
import com.example.exercise.repository.ContactRepository;
import com.example.exercise.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    public final CompanyRepository companyRepository;


    public final ContactRepository contactRepository;


    @Override
    public Page<Company> findAll(Pageable pageable) {
        return this.companyRepository.findAll(pageable);
    }

    @Override
    public List<Company> listAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company findById(Long id) {
        return this.companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
    }

    @Override
    public Company create(String CompanyName) {
        Company company = new Company(CompanyName);
        return  this.companyRepository.save(company);
    }

    @Override
    public Company edit(Long id, String CompanyName) {
        Company company = this.companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
        company.setName(CompanyName);
        return this.companyRepository.save(company);
    }
    @Override
    public void delete(Long id) {
        Company company = this.companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
        this.companyRepository.delete(company);
    }
    }
/*
    @Override
    public void delete(Long id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);

        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();

            // Find all contacts associated with the company
            List<Contact> contacts = contactRepository.findAllByCompany(company);

            // Delete each contact
            for (Contact contact : contacts) {
                contactRepository.delete(contact);
            }

            // Now you can safely delete the company
            companyRepository.delete(company);
        }
    }*/

