package com.example.exercise.service.impl;

import com.example.exercise.model.Company;
import com.example.exercise.model.Contact;
import com.example.exercise.model.Country;
import com.example.exercise.model.exeptions.CompanyNotFoundException;
import com.example.exercise.model.exeptions.ContactNotFoundException;
import com.example.exercise.model.exeptions.CountryNotFoundException;
import com.example.exercise.repository.CompanyRepository;
import com.example.exercise.repository.ContactRepository;
import com.example.exercise.repository.CountryRepository;
import com.example.exercise.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {
   private final ContactRepository contactRepository;
   private  final CompanyRepository companyRepository;
   private final CountryRepository countryRepository;

   @Override
   public List<Contact> listAll(){
    return this.contactRepository.findAll();
   }
    @Override
    public Page<Contact> findAll(Pageable pageable) {
        return this.contactRepository.findAll(pageable);
    }

    @Override
    public Contact findById(Long id) {
        return this.contactRepository.findById(id).orElseThrow(() ->new ContactNotFoundException(id)) ;
    }

 @Override
 public Contact create(String name, Long companyId, Long countryId) {
  Company company =  this.companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException(companyId));
  Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
  Contact contact1 = new Contact(name, company, country);
  return this.contactRepository.save(contact1);
 }

 @Override
 public Contact edit(Long id, String name, Long companyId, Long countryId) {
  Contact contact = this.contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
  Company company =  this.companyRepository.findById(companyId).orElseThrow(() -> new CompanyNotFoundException(companyId));
  Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
  contact.setName(name);
  contact.setCompany(company);
  contact.setCountry(country);
  return this.contactRepository.save(contact);
 }

 @Override
 public void delete(Long id) {
    Contact contact = this.contactRepository.findById(id).orElseThrow(() -> new ContactNotFoundException(id));
   contactRepository.delete(contact);
 }

 @Override
 public Page<Contact> listContactsByCountries(Country country, Pageable pageable) {
  return contactRepository.findAllByCountry(country,pageable);
 }

 @Override
 public Page<Contact> listContactsByCompanies(Company company,  Pageable pageable) {
  return contactRepository.findAllByCompany(company,pageable);
 }

 @Override
 public Page<Contact> listContactsByCountriesAndCompanies(Country country, Company company,  Pageable pageable) {
  return contactRepository.findAllByCountryAndCompany(country,company,pageable);
 }

    @Override
    public List<Contact> findContactsByCountries(Country country) {
        return contactRepository.listAllByCountry(country);
    }

    @Override
    public List<Contact> findContactsByCompanies(Company company) {
        return contactRepository.listAllByCompany(company);
    }

    @Override
    public List<Contact> findContactsByCountriesAndCompanies(Country country, Company company) {
        return contactRepository.listAllByCountryAndCompany(country,company);
    }
/*
 @Override
 public List<Contact> listContactsByCountries(Country country) {
   if(country!=null){
   return this.contactRepository.findAllByCountry(country);
  }else
  return this.contactRepository.findAll();
  }*/

 }



